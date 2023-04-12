package jpabook.jpashop.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다") //@Valid를 사용하면 필수 입력이 된다.
    private String name;

    private String city;
    private String street;
    private String zipcode;


}
