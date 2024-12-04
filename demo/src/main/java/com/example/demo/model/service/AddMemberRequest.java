package com.example.demo.model.service;

import lombok.*; 

import com.example.demo.model.domain.Member;

//입력값 필터링
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Data // getter, setter, toString, equals 등 자동 생성
public class AddMemberRequest {

    @NotNull(message = "이름은 필수 입력 사항입니다.") // null이 아니어야 한다는 검증
    @NotBlank(message = "이름은 공백일 수 없습니다.") // 공백이 아니어야 한다는 검증
    private String name;

    @NotNull(message = "이메일은 필수 입력 사항입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.") // 이메일 형식 검증
    private String email;

    @NotNull(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8글자 이상이어야 합니다.") // 비밀번호 길이 검증
    private String password;

    @NotNull(message = "나이는 필수 입력 사항입니다.")
    @Pattern(regexp = "^[1-9][0-9]?$|^90$", message = "나이는 19세 이상 90세 이하이어야 합니다.") // 나이 패턴 검증
    private String age;

    @NotNull(message = "모바일 번호는 필수 입력 사항입니다.")
    @Pattern(regexp = "^\\d{10,11}$", message = "모바일 번호는 10자리 또는 11자리 숫자여야 합니다.") // 모바일 번호 형식 검증
    private String mobile;

    @NotNull(message = "주소는 필수 입력 사항입니다.")
    @NotBlank(message = "주소는 공백일 수 없습니다.") // 공백이 아닌지 검증
    private String address;

    public Member toEntity(){ // Member 생성자를 통해 객체 생성
        return Member.builder()
            .name(name)
            .email(email)
            .password(password)
            .age(age)
            .mobile(mobile)
            .address(address)
            .build();
    }
}
