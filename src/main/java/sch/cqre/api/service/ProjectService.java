package sch.cqre.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import sch.cqre.api.domain.ProjectEntity;
import sch.cqre.api.dto.project.ProjectCreateRequestDto;
import sch.cqre.api.dto.project.ProjectEditRequestDto;
import sch.cqre.api.dto.project.ProjectResponseDto;
import sch.cqre.api.dto.project.ProjectWriteRequestDto;
import sch.cqre.api.repository.ProjectMemberRepository;
import sch.cqre.api.repository.ProjectRepository;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    // 프로젝트 조회
    public List<ProjectEntity> getProjectList() throws Exception {
        return projectRepository.findAll();
    }

    // 프로젝트 상세 조회
    public ProjectResponseDto getDetailProject(Long project_id){
        ProjectEntity project = projectRepository.findById(project_id).orElseThrow(()
        -> new IllegalArgumentException("해당 프로젝트는 존재하지 않습니다."));

        return new ProjectResponseDto(entity);
    }

    // 프로젝트 생성
    public Long write(ProjectWriteRequestDto requestDto){
        return projectRepository.save(requestDto.toEntity()).getProjectId();
    }
    public Long create(ProjectCreateRequestDto requestDto){
        return projectMemberRepository.save(requestDto.toEntity()).getMemberId();
    }

    // 프로젝트 수정
    public Long update(Long projectId, ProjectEditRequestDto projectEditRequestDto){
        ProjectEntity projectDto = projectRepository.findById(projectId)
            .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        projectDto.update(projectEditRequestDto.getProjectTitle(),
                        projectEditRequestDto.getProjectContent());

        return projectId;
    }

    // 프로젝트 삭제
    public void delete(Long project_id){
        ProjectEntity project = projectRepository.findById(project_id)
            .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트는 존재하지 않습니다."));

        projectRepository.delete(project);
    }
}
