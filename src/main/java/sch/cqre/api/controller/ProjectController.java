package sch.cqre.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import sch.cqre.api.dto.ProjectRequestDto;
import sch.cqre.api.service.ProjectService;

@RequiredArgsConstructor
@Controller
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/project/list")
    public String getProjectList(Model model, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "5") Integer size) throws Exception{
        try{
            model.addAttribute("resultMap", projectService.findAll(page, size));
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }

        return "/project/list";
    }

    @GetMapping("/project/write")
    public String getProjectWrite(Model model, ProjectRequestDto projectRequestDto){
        return "/project/write";
    }

    @GetMapping("/project/view")
    public String getProjectView(Model model, ProjectRequestDto projectRequestDto) throws Exception{
        try {
            if (projectRequestDto.getProjectId() != null) {
                model.addAttribute("info", projectService.findById(projectRequestDto.getProjectId()));
            }
        }catch(Exception e){
                throw new Exception(e.getMessage());
        }
        return "/project/view";
    }

    @PostMapping("/project/write/action")
    public String projectWriteAction(Model model, ProjectRequestDto projectRequestDto) throws Exception{
        try {
            Long result = projectService.save(projectRequestDto);
            if (result < 0) {
                throw new Exception("#Exception projectWriteAction!");
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return "redirect:/project/list";
    }

    @PostMapping("/project/view/action")
    public String projectViewAction(Model model, ProjectRequestDto projectRequestDto) throws Exception{
        try{
            int result = projectService.updateProject(projectRequestDto);
            if(result<1){
                throw new Exception("#Exception  projectViewAction!");
            }
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return "redirect:/project/list";
    }

    @PostMapping("/project/view/delete")
    public String projectViewDeleteAction(Model model, @RequestParam() Long projectId) throws Exception{
        try{
            projectService.deleteById(projectId);
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return "redirect:/project/list";
    }

    @PostMapping("/project/delete")
    public String projectDeleteAction(Model model, @RequestParam() Long[] deleteProjectId) throws Exception{
        try{
            projectService.deleteAll(deleteProjectId);
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return "redirect:/project/list";
    }
}
