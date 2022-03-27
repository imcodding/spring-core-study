package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy; // 필드 주입 방식 => 권장x. 스프링 테스트 할 때 종종 사용

    @Autowired // setter 주입 방식 => 선택(required 옵션). 변경O
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    @Autowired 생성자 주입 방식 => 필수. 변경X
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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
