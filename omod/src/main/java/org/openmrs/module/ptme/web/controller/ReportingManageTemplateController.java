package org.openmrs.module.ptme.web.controller;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingTemplate;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.GetIndicatorFromFrom;
import org.openmrs.module.ptme.forms.GetTemplateFormForm;
import org.openmrs.module.ptme.forms.TemplateForm;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class ReportingManageTemplateController {

    private PreventTransmissionService getPreventTransmissionService() {
        return Context.getService(PreventTransmissionService.class);
    }
    @ModelAttribute("chosenLocation")
    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }

    @RequestMapping(value = "/module/ptme/reportTemplate.form")
    public void manage(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "") String add,
                       @RequestParam(required = false, defaultValue = "") String delId,
                       @RequestParam(required = false, defaultValue = "") String templateId,
                       ModelMap modelMap) {

        if (!Context.isAuthenticated()){
            return;
        }
        HttpSession session = request.getSession();

        String mode = "list";
        if (!add.isEmpty()){
            mode = "form";
        }
        if (mode.equals("form")) {
            TemplateForm templateForm = new TemplateForm();

            modelMap.addAttribute("templateForm", templateForm);
        }
        if (mode.equals("list")){

            GetTemplateFormForm getTemplateFromFrom = new GetTemplateFormForm();
            modelMap.addAttribute("getTemplateFromFrom", getTemplateFromFrom);
            modelMap.addAttribute("template", getPreventTransmissionService().getAllTemplate(false));
        }
        modelMap.addAttribute("pageName", "Template.jsp");
        modelMap.addAttribute("mode", mode);
    }

    @RequestMapping(value = "/module/ptme/reportTemplate.form", method = RequestMethod.POST)
    public String onSubmitTemplate(HttpServletRequest request, ModelMap modelMap,
                                    @RequestParam(required = false, defaultValue = "") Integer templateId,
                                    TemplateForm templateForm, BindingResult result) throws IOException{

        if (!Context.isAuthenticated()){
            return null;
        }

        if(!result.hasErrors()) {
            HttpSession session = request.getSession();

            Boolean hasErrors = false;
            MultipartHttpServletRequest mpr = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = (Map<String, MultipartFile>)mpr.getFileMap();
            Set<String> foundResources = new HashSet<String>();
            for (String paramName : files.keySet()) {

                try {
                    String[] split = paramName.split("\\.", 2);
                    System.out.println(split[0]);
                    System.out.println(split[1]);
                    MultipartFile file = files.get(paramName);
                    String fileName = file.getOriginalFilename();
                    System.out.println(fileName);

                }
                catch (Exception e) {
                    throw new RuntimeException("Unable to add resource to design.", e);
                }
            }
            ReportingTemplate template = null;
            if (templateForm.getTemplateId() == null) {
                template = templateForm.getTemplate(new ReportingTemplate());
            }
           /* else {
                template = templateForm.getTemplate(getPreventTransmissionService().getTemplateById(templateForm.getTemplateId()));
            }

            if (getPreventTransmissionService().saveReportingTemplate(template) != null) {
                if (templateForm.getTemplateId() != null) {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Template mis à jour avec succès !");
                }
                else {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Template sauvegargé avec succès !");
                }
            }*/

            modelMap.addAttribute("mode", "list");
            return "redirect:/module/ptme/reportTemplate.form";
        }

        return null;
    }


}
