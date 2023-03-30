package goormton.tamrazu.server.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Eat {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eat_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alcohol_id")
	private Alcohol alcohol;

	public Eat(Member member, Alcohol alcohol) {
		this.member = member;
		this.alcohol = alcohol;
		alcohol.plusAteCount();
	}

	public void setMember(Member member) {
		if (Objects.nonNull(this.member)) {
			this.member.getAteAlcohols().remove(this);
		}
		this.member = member;
		member.getAteAlcohols().add(this);
	}

	public void setAlcohol(Alcohol alcohol) {
		if (Objects.nonNull(this.alcohol)) {
			this.alcohol.getAteMember().remove(this);
		}
		this.alcohol = alcohol;
		alcohol.getAteMember().add(this);
		alcohol.plusAteCount();
	}
}
