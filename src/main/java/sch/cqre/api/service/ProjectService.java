package sch.cqre.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sch.cqre.api.dto.ProjectRequestDto;
import sch.cqre.api.dto.ProjectResponseDto;
import sch.cqre.api.project.Project;
import sch.cqre.api.project.ProjectRepository;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public Long save(ProjectRequestDto projectSaveDto){
        return projectRepository.save(projectSaveDto.toEntity()).getProjectId();
    }

    @Transactional
    public HashMap<String, Object> findAll(Integer page, Integer size){
       HashMap<String, Object> resultMap = new HashMap<String, Object>();

       Page <Project> list = projectRepository.findAll(PageRequest.of(page, size));
       resultMap.put("list", list.stream().map(ProjectResponseDto::new).collect(Collectors.toList()));
       resultMap.put("paging", list.getPageable());
       resultMap.put("totalPage", list.getTotalPages());

       return resultMap;
    }

    public ProjectResponseDto findById(Long projectId){
        return new ProjectResponseDto(projectRepository.findById(projectId).get());
    }

    public int updateProject(ProjectRequestDto projectRequestDto){
        return projectRepository.updateProject(projectRequestDto);
    }

    public void deleteById(Long projectId){
        projectRepository.deleteById(projectId);
    }

    public void deleteAll(Long[] delteId){
        projectRepository.deleteProject(delteId);
    }
}
