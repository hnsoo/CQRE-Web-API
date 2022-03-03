package sch.cqre.api.dto;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.PostEntity;

@Getter
@NoArgsConstructor
public class PostResponseDto extends PostEntity{

}
