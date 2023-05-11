package com.tenco.blog.test_controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

@RestController // IoC
public class DummyControllerTest {

	// @Autowired 대신에 생성자 쓰는 방식도 연습해보자.
	private UserRepository userRepository;
	
	public DummyControllerTest(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// MIME TYPE : application/json
	// 회원 등록 - 샘플
	// 매핑 엔드포인트랑 메서드 이름을 약간 맞춰주기 
	@PostMapping("/dummy/insert-user")
	public String insertUser(@Validated @RequestBody User user) {
		// 유효성 검사 생략
		System.out.println(user.toString());
		
		user.setRole("user");
		// insert
		userRepository.save(user);
		System.out.println("여기 코드 동작하나요?");
		
		return "회원가입 성공";
	}
	
	@GetMapping("/dummy/user/{id}")
	public User getUser(@PathVariable Integer id) {
		// 인증검사, 유효성 검사 생략
		
		// Optional 타입
		// User가 있을 수도 있고, null일 수도 있음.
		
		// Optional<User> userOp = userRepository.findById(id);
		// 바로 User로 가져오기
		// get() : User 객체가 null인 경우가 없을 때 사용함
		// orElseGet(객체 또는 null) : 데이터가 있으면 그대로 반환, 없으면 매개변수에 정의한 객체 반환 (함수도 가능)
		// orElseThrow() : 데이터가 있으면 그대로 반환, 없으면 예외로 던짐
		
		// 1
//		User user = userRepository.findById(id).get(); 
		
		// 2
//		User user = userRepository.findById(id).orElseGet(() -> {
//			return new User();
//		}); 
		
		// 3
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없네요");
		});
		
		return user;
	}
	
	// 페이징 처리도 자동으로 가능
	// ?page=0 : 첫 번째 페이지에 반환할 객체들 가져옴 (page 번호는 0번부터 시작)
	
//	@GetMapping("/dummy/users")
//	public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable) { 
//		// List<User> users = userRepository.findAll();
//		Page<User> pageUser = userRepository.findAll(pageable);
//		return pageUser;
//	}
	
	@GetMapping("/dummy/users")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable) { 
		// List<User> users = userRepository.findAll();
		Page<User> pageUser = userRepository.findAll(pageable);
		
		// List<User>만 뽑아내세요.
		List<User> user = pageUser.getContent();
		System.out.println("전체 페이지 수 : " + pageUser.getTotalPages());
		
		return user;
	}
	
	// JSON 던질 예정
	// update를 할 때, 1번 방식 : 기존 로직 처리
	//                 2번 방식 : dirty checking 사용 (영속성 컨텍스트가 있어서 가능함)
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable Integer id, @Validated @RequestBody User reqUser) {
		
		// 인증 검사, 유효성 검사 
		
		// 수정 기능을 만들 때에는 존재 여부 확인부터 (그 사이에 데이터가 삭제될 수도 있기 때문임)
		// 영속화된 데이터
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
		});
		
		// 클라이언트가 던진 데이터 reqUser
		userEntity.setEmail(reqUser.getEmail());
		userEntity.setPassword(reqUser.getPassword());
		
		// 저장 처리
//		userRepository.save(userEntity); // (1번 방식) update가 따로 있지 않고, 이렇게 하면 자동으로 인식함
		
		// 더티 체킹 (@Transactional 지정) -> save 메서드를 호출하지 않아도 변경 처리됨
		// 트랜잭션 내에서, 트랜잭션이 끝나기 전에 영속성 컨텍스트의 1차 캐시에 들어가 있는 데이터 상태를 변경 감지함
		
		// 처음에 select해서 가져온 User 데이터가 영속성 컨텍스트에 남아있는 상태.
		// 그 상태에서 setter 메서드로 속성값을 변경함
		// 변경된 상태를 감지하고, 자동으로 commit되어서 변경된 상태가 DB에 반영되는 것임
		
		return userEntity;
	}
	
	
}
