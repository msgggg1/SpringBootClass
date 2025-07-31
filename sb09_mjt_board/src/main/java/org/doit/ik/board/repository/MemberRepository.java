package org.doit.ik.board.repository;

import org.doit.ik.board.entry.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{

}
