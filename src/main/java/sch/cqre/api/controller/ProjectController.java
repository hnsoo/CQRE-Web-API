package sch.cqre.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import sch.cqre.api.domain.ProjectEntity;
import sch.cqre.api.dto.project.ProjectCreateRequestDto;
import sch.cqre.api.dto.project.ProjectEditRequestDto;
import sch.cqre.api.dto.project.ProjectResponseDto;
import sch.cqre.api.dto.project.ProjectWriteRequestDto;
import sch.cqre.api.service.ProjectService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    //프로젝트 조회
    @GetMapping("/list")
    public ResponseEntity<List<ProjectEntity>> getProjectList() throws Exception{
       return ResponseEntity.ok().body(projectService.getProjectList());
    }

    // 프로젝트 상세 조회
    @GetMapping("/list/{project_id}")
    public ProjectResponseDto getDetailProject(@PathVariable Long project_id) throws Exception{
        return projectService.getDetailProject(project_id);
    }

    // 프로젝트 작성
    @PostMapping("/write")
    public Long postWriteProject(@RequestBody ProjectWriteRequestDto requestDto){
        return projectService.write(requestDto);
    }
    @PostMapping("/write")
    public Long postCreateProject(@RequestBody ProjectCreateRequestDto requestDto){
        return projectService.create(requestDto);
    }


    // 프로젝트 수정
    @PatchMapping("/project/{project_id}")
    public Long patchEditProject(@PathVariable Long projectId, @RequestBody ProjectEditRequestDto requestDto){
        return projectService.update(projectId, requestDto);
    }

    // 프로젝트 삭제
    @DeleteMapping("/project/{project_id}")
    public void deleteProject(@PathVariable Long project_id){
        projectService.delete(project_id);
    }
}
