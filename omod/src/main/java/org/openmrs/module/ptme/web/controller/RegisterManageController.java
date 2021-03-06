package org.openmrs.module.ptme.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.*;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.*;
import org.openmrs.module.ptme.forms.validators.BirthFormValidator;
import org.openmrs.module.ptme.forms.validators.PostnatalFormValidator;
import org.openmrs.module.ptme.forms.validators.PrenatalFormValidator;
import org.openmrs.module.ptme.utils.ConsultationWithType;
import org.openmrs.module.ptme.utils.UsefullFunction;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class RegisterManageController {

    protected final Log log = LogFactory.getLog(getClass());

    @ModelAttribute("listConsultation")
    public List<ConsultationWithType> getListConsultation() {
        return getPreventTransmissionService().getConsultationsByDate(UsefullFunction.formatDateToddMMyyyy(new Date()), false);
    }

    @ModelAttribute("chosenLocation")
    public Location getChosenLocation(Integer locationId) {
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }

    private PreventTransmissionService getPreventTransmissionService() {
        return Context.getService(PreventTransmissionService.class);
    }

    @RequestMapping(value = "/module/ptme/register.form", method = RequestMethod.GET)
    public void manage(ModelMap modelMap,
                       HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "") String register,
                       @RequestParam(required = false, defaultValue = "") Date sDate,
                       @RequestParam(required = false, defaultValue = "") Date eDate,
                       @RequestParam(required = false, defaultValue = "") Integer page,
                       @RequestParam(required = false, defaultValue = "") Integer size) {

        if(Context.isAuthenticated()) {

            HttpSession session = request.getSession();

            ManageConsultationForm manageConsultationForm = new ManageConsultationForm();

            if (!register.isEmpty()){
                manageConsultationForm.setRegister(register);
            }
            if(sDate != null) {
                manageConsultationForm.setsDate(sDate);
            }
            if(eDate != null) {
                manageConsultationForm.seteDate(eDate);
            }

            modelMap.addAttribute("manageConsultationForm", manageConsultationForm);

            if (register.equals("Prenatal")) {
                List<Prenatal> prenatalList = getPreventTransmissionService().getPrenatalConsultationsByDate(sDate, eDate);
                modelMap.addAttribute("consultationList", prenatalList);
            } else if(register.equals("Birth")){
                List<Birth> birthList = getPreventTransmissionService().getBirthConsultationsByDate(sDate, eDate);
                modelMap.addAttribute("consultationList", birthList);
            } else if(register.equals("Postnatal")){
                List<Postnatal> postnatalList = getPreventTransmissionService().getPostnatalConsultationsByDate(sDate, eDate);
                modelMap.addAttribute("consultationList", postnatalList);
            }

            modelMap.addAttribute("register ", register);

        }
    }

    @RequestMapping(value = "/module/ptme/registerList.form", method = RequestMethod.GET)
    public void registerList(HttpServletRequest request, ModelMap model,
                             @RequestParam(required = false, defaultValue = "") String register,
                             @RequestParam(required = false, defaultValue = "") String mode,
                             @RequestParam(required = false, defaultValue = "") Integer consultationId,
                             @RequestParam(required = false, defaultValue = "") Integer delId,
                             @RequestParam(required = false, defaultValue = "") String pregnantNumber) {


        if(Context.isAuthenticated())
        {
            HttpSession session = request.getSession();

            OpenRegisterForm openRegisterForm = new OpenRegisterForm();

            mode = "list";

            if(delId != null) {
                if (register.equals("Prenatal")) {
                    Prenatal prenatal = getPreventTransmissionService().getPrenatalConsultation(delId);
                    if(prenatal != null) {
                        log.info("Deleting prenatal consultation with code = " + delId.toString());
                        prenatal.setVoided(true);
                        prenatal.getHivService().setVoided(prenatal.getVoided());
                        prenatal.setVoidedBy(Context.getAuthenticatedUser());
                        prenatal.getHivService().setVoidedBy(prenatal.getVoidedBy());
                        prenatal.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                        prenatal.getHivService().setDateVoided(prenatal.getDateVoided());
                        getPreventTransmissionService().savePrenatalConsultation(prenatal);
                    }
                } else if(register.equals("Birth")) {
                    Birth birth = getPreventTransmissionService().getBirthConsultation(delId);
                    log.info("Deleting birth consultation with code = " + delId.toString());
                    if(birth != null) {
                        birth.setVoided(true);
                        birth.getHivService().setVoided(birth.getVoided());
                        birth.setVoidedBy(Context.getAuthenticatedUser());
                        birth.getHivService().setVoidedBy(birth.getVoidedBy());
                        birth.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                        birth.getHivService().setDateVoided(birth.getDateVoided());
                        getPreventTransmissionService().saveBirthConsultation(birth);
                    }

                } else if (register.equals("Postnatal")) {
                    Postnatal postnatal = getPreventTransmissionService().getPostnatalConsultation(delId);
                    log.info("Deleting postnatal consultation with code = " + delId.toString());
                    if (postnatal != null) {
                        postnatal.setVoided(true);
                        postnatal.getHivService().setVoided(postnatal.getVoided());
                        postnatal.setVoidedBy(Context.getAuthenticatedUser());
                        postnatal.getHivService().setVoidedBy(postnatal.getVoidedBy());
                        postnatal.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                        postnatal.getHivService().setDateVoided(postnatal.getDateVoided());
                        getPreventTransmissionService().savePostnatalConsultation(postnatal);
                    }
                }
                model.addAttribute("openRegisterForm", openRegisterForm);

            }
            else if((pregnantNumber.isEmpty() || register.isEmpty()) && consultationId == null) {
                openRegisterForm.setPregnantNumber(pregnantNumber);
                openRegisterForm.setRegister(register);
                model.addAttribute("openRegisterForm", openRegisterForm);
            } else  {
                mode = "form";

                ConsultationForm consultationForm = new ConsultationForm();
                PregnantPatient pregnantPatient;

                log.info("Entering in form mode");

                if(consultationId != null) {

                    if(register .equals("Prenatal")) {
                        consultationForm.getPrenatalConsultation(getPreventTransmissionService().getPrenatalConsultation(consultationId));
                    }
                    else if (register.equals("Birth")) {
                        consultationForm.getBirthConsultation(getPreventTransmissionService().getBirthConsultation(consultationId));
                    }
                    else if (register.equals("Postnatal")) {
                        consultationForm.getPostnatalConsultation(getPreventTransmissionService().getPostnatalConsultation(consultationId));
                    }
                    pregnantPatient = getPreventTransmissionService().getPregnantPatientById(consultationForm.getPregnantPatientId());
                } else {
                    pregnantPatient = getPreventTransmissionService().getPregnantPatientByPregnantNumber(pregnantNumber);
                    if (pregnantPatient != null) {
                        consultationForm.setPregnantPatient(pregnantPatient);
                    } else {
                        consultationForm.setPregnantNumber(pregnantNumber);
                    }
                }

                model.addAttribute("consultationForm", consultationForm);
                model.addAttribute("pregnantPatient", pregnantPatient);
                model.addAttribute("register", register);
            }

        }
        model.addAttribute("mode", mode);

    }

    @RequestMapping(value = "/module/ptme/registerList.form", method = RequestMethod.POST)
    public String onSubmit(HttpServletRequest request, Model model,
                           @RequestParam(defaultValue = "") String register,
                           @RequestParam(required = false, defaultValue = "") Integer consultationId,
//                           @RequestParam(required = false, defaultValue = "") String pregnantNumber,
                         ConsultationForm consultationForm,
                         BindingResult result)
    {
        if (Context.isAuthenticated()) {

            if (register.equals("Prenatal")){
                new PrenatalFormValidator().validate(consultationForm, result);
            } else if (register.equals("Birth")){
                new BirthFormValidator().validate(consultationForm, result);
            } else if (register.equals("Postnatal")) {
                new PostnatalFormValidator().validate(consultationForm, result);
            }

            model.addAttribute("mode", "form");
            model.addAttribute("register", register);

            if(!result.hasErrors()) {

                HttpSession session = request.getSession();

                PregnantPatient pregnantPatient;
                if(consultationForm.getPregnantPatientId() == null) {
                    pregnantPatient = consultationForm.getPregnantPatient(new PregnantPatient());
                } else {
                    pregnantPatient = consultationForm.getPregnantPatient(getPreventTransmissionService().getPregnantPatientById(consultationForm.getPregnantPatientId()));
                }

                if(!consultationForm.getHivCareNumber().isEmpty()) {

                    PregnantPatient existingPregnantPatient = getPreventTransmissionService().getPregnantPatientByHivCareNumber(consultationForm.getHivCareNumber());
                    if (existingPregnantPatient != null) {
                        if (consultationForm.getPregnantPatientId() != null) {

                            if (!existingPregnantPatient.getPregnantNumber().equals(consultationForm.getPregnantNumber())) {

                                session.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
                                        "Ce numéro de prise en charge appartient déjà à une femme enceinte : " + existingPregnantPatient.getPregnantNumber());
                                return null;
                            }
                        } else {
                            session.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
                                    "Ce numéro de prise en charge appartient déjà à une femme enceinte : " + existingPregnantPatient.getPregnantNumber());
                            return null;
                        }
                    }
                }

                if (getPreventTransmissionService().savePregnantPatient(pregnantPatient) != null){

                    if(register.equals("Birth")) {
                        if (consultationForm.getConsultationId() == null) {
                            Birth birth = new Birth();
                            birth.setPregnantPatient(pregnantPatient);
                            consultationId = getPreventTransmissionService()
                                    .saveBirthConsultation(consultationForm.setBirthConsultationValues(birth)).getConsultationId();
                        } else {
                            consultationId = getPreventTransmissionService()
                                    .saveBirthConsultation(consultationForm
                                            .setBirthConsultationValues(getPreventTransmissionService()
                                                    .getBirthConsultation(consultationForm.getConsultationId()))).getConsultationId();
                        }
                    } else if(register.equals("Prenatal")) {
                        if (consultationForm.getConsultationId() == null) {
                            Prenatal prenatal = new Prenatal();
                            prenatal.setPregnantPatient(pregnantPatient);
                            consultationId = getPreventTransmissionService()
                                    .savePrenatalConsultation(consultationForm.setPrenatalConsultationValues(prenatal)).getConsultationId();
                        } else {
                            consultationId = getPreventTransmissionService()
                                    .savePrenatalConsultation(consultationForm
                                            .setPrenatalConsultationValues(getPreventTransmissionService()
                                                    .getPrenatalConsultation(consultationForm.getConsultationId()))).getConsultationId();
                        }
                    } else if(register.equals("Postnatal")) {
                        if (consultationForm.getConsultationId() == null) {
                            Postnatal postnatal = new Postnatal();
                            postnatal.setPregnantPatient(pregnantPatient);
                            consultationId = getPreventTransmissionService()
                                    .savePostnatalConsultation(consultationForm.setPostnatalConsultationValues(postnatal)).getConsultationId();
                        } else {
                            consultationId = getPreventTransmissionService()
                                    .savePostnatalConsultation(consultationForm
                                            .setPostnatalConsultationValues(getPreventTransmissionService()
                                                    .getPostnatalConsultation(consultationForm.getConsultationId()))).getConsultationId();
                        }
                    }

                    if (consultationId != null) {
                        HivService hivService;

                        if(consultationForm.getConsultationId() == null){
                            HivService h = new HivService();
                            hivService = consultationForm.getHivService(h);

                        } else {
                            HivService h = getPreventTransmissionService().getHivServiceById(consultationForm.getHivServiceId());
                            hivService = consultationForm.getHivService(h);
                        }

                        hivService.setConsultation(getPreventTransmissionService().getConsultation(consultationId));
                        getPreventTransmissionService().saveHivService(hivService);

                        if(consultationForm.getConsultationId() != null) {
                            session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "ptme.updated");
                        } else {
                            session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "ptme.saved");
                        }

                        OpenRegisterForm openRegisterForm = new OpenRegisterForm();

                        model.addAttribute("openRegisterForm", openRegisterForm);
                        model.addAttribute("mode", "list");

                        return "redirect:/module/ptme/registerList.form";
                    }
                }


            }

        }
        return null;
    }
}
