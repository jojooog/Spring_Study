package hello.spring_core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    //member 객체를 저장하고 찾아올 map
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
            store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        Member findMember = store.get(id);
        return findMember;
    }
}
