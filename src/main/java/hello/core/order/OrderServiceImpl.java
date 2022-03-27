package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* 생성자 주입 방식을 사용해라!
    1. 대부분의 의존 관계는 애플리케이션 종료 전까지 변경되면 안 된다.
    2. setter 주입 방식은 public 로 열어둬야해서 취약하고, 누군가 실수로 변경할 수 있다.
    3. 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 살릴 수 있다.
    **/
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member= memberRepository.findById(memberId);
        // SRP 지킴. 주문과 할인 분리함. 할인에 관련된 건 discount 쪽만 변경하면 됨.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Configuration singleton 테스트 확인
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
