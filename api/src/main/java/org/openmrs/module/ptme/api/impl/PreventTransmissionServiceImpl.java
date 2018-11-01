/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.ptme.api.impl;

import org.openmrs.Patient;
import org.openmrs.Relationship;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ptme.*;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.api.db.PreventTransmissionDAO;
import org.openmrs.module.ptme.utils.*;

import java.util.Date;
import java.util.List;

/**
 * It is a default implementation of {@link PreventTransmissionService}.
 */
public class PreventTransmissionServiceImpl extends BaseOpenmrsService implements PreventTransmissionService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private PreventTransmissionDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(PreventTransmissionDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public PreventTransmissionDAO getDao() {
	    return dao;
    }


    /**
     * Pregnant Patient
     */

    @Override
    public List<PregnantPatient> getAllPregnantPatient() {
        return dao.getAllPregnantPatient();
    }

    @Override
    public PregnantPatient getPregnantPatientById(Integer id) {
        return dao.getPregnantPatientById(id);
    }

    @Override
    public PregnantPatient savePregnantPatient(PregnantPatient pregnantPatient) {
        return dao.savePregnantPatient(pregnantPatient);
    }

    @Override
    public void deletePregnantPatient(PregnantPatient pregnantPatient) {
        dao.deletePregnantPatient(pregnantPatient);
    }

    @Override
    public PregnantPatient voidPregnantPatient(Integer id) {
        return dao.voidPregnantPatient(id);
    }

    @Override
    public List<PregnantPatient> getAllPregnantPatientByVoided(Boolean voidedIncluded) {
        return dao.getAllPregnantPatientByVoided(voidedIncluded);
    }

    @Override
    public PregnantPatient getPregnantPatientByPregnantNumber(String pregnantNumber) {
        return dao.getPregnantPatientByPregnantNumber(pregnantNumber);
    }

    @Override
    public PregnantPatient getPregnantPatientByHivCareNumber(String hivCareNumber) {
        return dao.getPregnantPatientByHivCareNumber(hivCareNumber);
    }

    /**
     * HIV Service
     */

    @Override
    public List<HivService> getAllHivService() {
        return dao.getAllHivService();
    }

    @Override
    public HivService getHivServiceById(Integer id) {
        return dao.getHivServiceById(id);
    }

    @Override
    public HivService saveHivService(HivService hivService) {
        return dao.saveHivService(hivService);
    }

    @Override
    public void deleteHivService(HivService hivService) {
        dao.deleteHivService(hivService);
    }

//    @Override
//    public HivService voidHivService(Integer id) {
//        return dao.voidHivService(id);
//    }

    @Override
    public List<HivService> getAllHivServiceByVoided(Boolean voidedIncluded) {
        return dao.getAllHivServiceByVoided(voidedIncluded);
    }

    /**
     * Consultations
     */

    @Override
    public Birth saveBirthConsultation(Birth birth) {
        return dao.saveBirthConsultation(birth);
    }

    @Override
    public Prenatal savePrenatalConsultation(Prenatal prenatal) {
        return dao.savePrenatalConsultation(prenatal);
    }

    @Override
    public Postnatal savePostnatalConsultation(Postnatal postnatal) {
        return dao.savePostnatalConsultation(postnatal);
    }

    @Override
    public Consultation getConsultation(Integer id) {
        return dao.getConsultation(id);
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return dao.getAllConsultations();
    }

    @Override
    public List<Consultation> getAllConsultationsByDate(Date currentDate, Boolean voided) {
        return dao.getAllConsultationsByDate(currentDate, voided);
    }

    @Override
    public List<ConsultationWithType> getConsultationsByDate(Date currentDate, Boolean voided) {
        return dao.getConsultationsByDate(currentDate, voided);
    }

    @Override
    public List<Consultation> getConsultationsByDate(Date startDate, Date endDate) {
        return dao.getConsultationsByDate(startDate, endDate);
    }

    @Override
    public List<Birth> getAllBirthConsultation() {
        return dao.getAllBirthConsultation();
    }

    @Override
    public List<Prenatal> getAllPrenatalConsultation() {
        return dao.getAllPrenatalConsultation();
    }

    @Override
    public List<Postnatal> getAllPostnatalConsultation() {
        return dao.getAllPostnatalConsultation();
    }

    @Override
    public List<Birth> getBirthConsultationsByDate(Date sDate, Date eDate) {
        return dao.getBirthConsultationsByDate(sDate, eDate);
    }

    @Override
    public List<Prenatal> getPrenatalConsultationsByDate(Date startDate, Date endDate) {
        return dao.getPrenatalConsultationsByDate(startDate, endDate);
    }

    @Override
    public List<Postnatal> getPostnatalConsultationsByDate(Date sDate, Date eDate) {
        return dao.getPostnatalConsultationsByDate(sDate, eDate);
    }

    @Override
    public Birth getBirthConsultation(Integer id) {
        return dao.getBirthConsultation(id);
    }

    @Override
    public Prenatal getPrenatalConsultation(Integer id) {
        return dao.getPrenatalConsultation(id);
    }

    @Override
    public Postnatal getPostnatalConsultation(Integer id) {
        return dao.getPostnatalConsultation(id);
    }

    @Override
    public List<Prenatal> getPrenatalConsultationsByPregnantPatientNumber(String pregnantNumber) {
        return dao.getPrenatalConsultationsByPregnantPatientNumber(pregnantNumber);
    }

    @Override
    public List<Postnatal> getPostnatalConsultationsByPregnantPatientNumber(String pregnantNumber) {
        return dao.getPostnatalConsultationsByPregnantPatientNumber(pregnantNumber);
    }

    @Override
    public MotherFollowup getCurrentMotherFollowupByPregnantPatient(PregnantPatient pregnantPatient) {
        return dao.getCurrentMotherFollowupByPregnantPatient(pregnantPatient);
    }

    @Override
    public void removeMotherFollowupVisit(MotherFollowupVisit motherFollowupVisit) {
        dao.removeMotherFollowupVisit(motherFollowupVisit);
    }

    @Override
    public MotherFollowup getMotherFollowupById(Integer motherFollowupId) {
        return dao.getMotherFollowupById(motherFollowupId);
    }

    @Override
    public MotherFollowupVisit getMotherFollowUpVisitById(Integer id) {
        return dao.getMotherFollowUpVisitById(id);
    }

    @Override
    public MotherFollowup saveMotherFollowup(MotherFollowup motherFollowup) {
        return dao.saveMotherFollowup(motherFollowup);
    }

    @Override
    public MotherFollowupVisit saveMotherFollowupVisit(MotherFollowupVisit motherFollowupVisit) {
        return dao.saveMotherFollowupVisit(motherFollowupVisit);
    }

    @Override
    public MotherFollowupVisit getPregnantPatientFollowupByDate(Integer pregnantPatientId, Date visitDate) {
        return dao.getPregnantPatientFollowupByDate(pregnantPatientId, visitDate);
    }

    @Override
    public MotherFollowupVisit getEarlierPregnantPatientFollowupVisitForFollowup(Integer motherFollowupId) {
        return dao.getEarlierPregnantPatientFollowupVisitForFollowup(motherFollowupId);
    }

    @Override
    public Child getChildByFollowupNumber(String childFollowupNumber) {
        return dao.getChildByFollowupNumber(childFollowupNumber);
    }

    @Override
    public List<PregnantPatientToFollow> getPregnantPatientFollowupList() {
        return dao.getPregnantPatientFollowupList();
    }

    @Override
    public Patient getPatientByIdentifier(String identifier) {
        return dao.getPatientByIdentifier(identifier);
    }

    @Override
    public List<MotherFollowupCurrentlyOn> getMotherFollowupCurrentlyOnList(Date startDate, String status, Integer pregnancyOutcome, Date endDate) {
        return dao.getMotherFollowupCurrentlyOnList(startDate, status, pregnancyOutcome, endDate);
    }

    @Override
    public List<MotherFollowupCurrentlyOn> getMotherFollowupList(Date startDate, Date endDate, String status, Integer pregnancyOutcome, String startOrEnd) {
        return dao.getMotherFollowupList(startDate, endDate, status, pregnancyOutcome, startOrEnd);
    }

    @Override
    public List<Child> getChildList() {
        return dao.getChildList();
    }

    @Override
    public Child getChildById(Integer childId) {
        return dao.getChildById(childId);
    }

    @Override
    public Child saveChild(Child child) {
        return dao.saveChild(child);
    }

    @Override
    public ChildFollowup getChildFollowupById(Integer childFollowupId) {
        return dao.getChildFollowupById(childFollowupId);
    }

    @Override
    public ChildFollowupVisit getChildFollowupVisitById(Integer childFollowupVisitId) {
        return dao.getChildFollowupVisitById(childFollowupVisitId);
    }

    @Override
    public ChildFollowup saveChildFollowup(ChildFollowup childFollowup) {
        return dao.saveChildFollowup(childFollowup);
    }

    @Override
    public ChildFollowupVisit saveChildFollowupVisit(ChildFollowupVisit childFollowupVisit) {
        return dao.saveChildFollowupVisit(childFollowupVisit);
    }

    @Override
    public ChildFollowupVisit getChildFollowupVisitByChildAndDate(Integer childId, Date visitDate) {
        return dao.getChildFollowupVisitByChildAndDate(childId, visitDate);
    }

    @Override
    public List<ChildFollowupVisit> getChildFollowupVisitByChild(Integer childId) {
        return dao.getChildFollowupVisitByChild(childId);
    }

    @Override
    public void deleteChildFollowupVisit(ChildFollowupVisit childFollowupVisit) {
        dao.deleteChildFollowupVisit(childFollowupVisit);
    }

    @Override
    public List<ChildFollowupTransformer> getChildFollowupList(String status, Date startDate, Date endDate) {
        return dao.getChildFollowupList(status, startDate, endDate);
    }

    @Override
    public void deleteChildFollowup(ChildFollowup childFollowup) {
        dao.deleteChildFollowup(childFollowup);
    }

    @Override
    public List<MotherFollowupVisit> getMotherFollowupVisitByPatientAndFollowup(MotherFollowup motherFollowup) {
        return dao.getMotherFollowupVisitByPatientAndFollowup(motherFollowup);
    }

    @Override
    public Relationship getChildRelationship(Patient mother, Patient patient) {
        return dao.getChildRelationship(mother, patient);
    }

    @Override
    public Consultation getPatientConsultationByDate(Integer pregnantPatientId, Date consultationDate) {
        return dao.getPatientConsultationByDate(pregnantPatientId, consultationDate);
    }

    @Override
    public List<MotherFollowupAppointment> getPregnantPatientsAppointment() {
        return dao.getPregnantPatientsAppointment();
    }

    @Override
    public List<MotherFollowupAppointment> getPregnantPatientsAppointmentMissed() {
        return dao.getPregnantPatientsAppointmentMissed();
    }

    @Override
    public List<ChildFollowupAppointment> getChildByAppointment() {
        return dao.getChildByAppointment();
    }

    @Override
    public List<ChildFollowupAppointment> getChildByAppointmentMissed() {
        return dao.getChildByAppointmentMissed();
    }

    @Override
    public Boolean isDead(Patient patient) {
        return dao.isDead(patient);
    }

    @Override
    public Boolean isTransfered(Patient patient) {
        return dao.isTransfered(patient);
    }

    @Override
    public List<ReportingTemplate> getAllTemplate() {
        return dao.getAllTemplate();
    }

    @Override
    public List<ReportingTemplate> getAllTemplate(Boolean includeVoided) {
        return dao.getAllTemplate(includeVoided);
    }

    @Override
    public ReportingTemplate getTemplateById(Integer templateId) {
        return dao.getTemplateById(templateId);
    }

    @Override
    public ReportingTemplate saveReportingTemplate(ReportingTemplate template) {
        return dao.saveReportingTemplate(template);
    }

    @Override
    public Boolean removeTemplate(Integer templateId) {
        return dao.removeTemplate(templateId);
    }

    @Override
    public ReportingTemplate voidTemplate(Integer templateId) {
        return dao.voidTemplate(templateId);
    }



    @Override
    public List<ReportingIndicator> getAllIndicators() {
        return dao.getAllIndicators();
    }

    @Override
    public List<ReportingIndicator> getAllIndicators(Boolean includeVoided) {
        return dao.getAllIndicators(includeVoided);
    }

    @Override
    public ReportingIndicator getIndicatorById(Integer indicatorId) {
        return dao.getIndicatorById(indicatorId);
    }

    @Override
    public ReportingIndicator saveReportingIndicator(ReportingIndicator indicator) {
        return dao.saveReportingIndicator(indicator);
    }

    @Override
    public Boolean removeIndicator(Integer indicatorId) {
        return dao.removeIndicator(indicatorId);
    }

    @Override
    public ReportingIndicator voidIndicator(Integer indicatorId) {
        return dao.voidIndicator(indicatorId);
    }
}