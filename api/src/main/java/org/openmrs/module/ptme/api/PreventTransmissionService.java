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
package org.openmrs.module.ptme.api;

import org.openmrs.Patient;
import org.openmrs.Relationship;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.ptme.*;
import org.openmrs.module.ptme.utils.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(PreventTransmissionService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface PreventTransmissionService extends OpenmrsService {
     
	/**
	 * Pregnant Patient
	 * 
	 */

	List<PregnantPatient> getAllPregnantPatient();
	PregnantPatient getPregnantPatientById(Integer id);
	PregnantPatient savePregnantPatient(PregnantPatient pregnantPatient);
	void deletePregnantPatient(PregnantPatient pregnantPatient);
	PregnantPatient voidPregnantPatient(Integer id);
	List<PregnantPatient> getAllPregnantPatientByVoided(Boolean voidedIncluded);
	PregnantPatient getPregnantPatientByPregnantNumber(String pregnantNumber);
	PregnantPatient getPregnantPatientByHivCareNumber(String hivCareNumber);

	/**
	 * Hiv Service
	 */

	List<HivService> getAllHivService();
	HivService getHivServiceById(Integer id);
	HivService saveHivService(HivService hivService);
	void deleteHivService(HivService hivService);
//	HivService voidHivService(Integer id);
	List<HivService> getAllHivServiceByVoided(Boolean voidedIncluded);

	/**
	 * Consultations
	 */

	Birth saveBirthConsultation(Birth birth);
	Prenatal savePrenatalConsultation(Prenatal prenatal);
	Postnatal savePostnatalConsultation(Postnatal postnatal);
	//    Birth updateBirthConsultation(Integer id);
//    Prenatal updatePrenatalConsultation(Integer id);
//    Postnatal updatePostnatalConsultation(Integer id);
	Consultation getConsultation(Integer id);
	List<Consultation> getAllConsultations();
	List<Consultation> getAllConsultationsByDate(Date currentDate, Boolean voided);
	List<ConsultationWithType> getConsultationsByDate(Date currentDate, Boolean voided);
	List<Consultation> getConsultationsByDate(Date startDate, Date endDate);
	List<Birth> getAllBirthConsultation();
	List<Prenatal> getAllPrenatalConsultation();
	List<Postnatal> getAllPostnatalConsultation();
	List<Birth> getBirthConsultationsByDate(Date sDate, Date dateConsultation);
	List<Prenatal> getPrenatalConsultationsByDate(Date startDate, Date endDate);
	List<Postnatal> getPostnatalConsultationsByDate(Date sDate, Date eDate);
	Birth getBirthConsultation(Integer id);
	Prenatal getPrenatalConsultation (Integer id);
	Postnatal getPostnatalConsultation (Integer id);

	List<Prenatal> getPrenatalConsultationsByPregnantPatientNumber(String pregnantNumber);
	List<Postnatal> getPostnatalConsultationsByPregnantPatientNumber(String pregnantNumber);

	// Mother Followup

	MotherFollowup getCurrentMotherFollowupByPregnantPatient(PregnantPatient pregnantPatient);
	void removeMotherFollowupVisit(MotherFollowupVisit motherFollowupVisit);
    MotherFollowup getMotherFollowupById(Integer motherFollowupId);
	MotherFollowupVisit getMotherFollowUpVisitById(Integer id);
	MotherFollowup saveMotherFollowup(MotherFollowup motherFollowup);
	MotherFollowupVisit saveMotherFollowupVisit(MotherFollowupVisit motherFollowupVisit);
	MotherFollowupVisit getPregnantPatientFollowupByDate(Integer pregnantPatientId, Date visitDate);
	MotherFollowupVisit getEarlierPregnantPatientFollowupVisitForFollowup(Integer motherFollowupId);

    Child getChildByFollowupNumber(String childFollowupNumber);

    List<PregnantPatientToFollow> getPregnantPatientFollowupList();

    Patient getPatientByIdentifier(String identifier);

	List<MotherFollowupCurrentlyOn> getMotherFollowupCurrentlyOnList(Date startDate, String status, Integer pregnancyOutcome, Date endDate);
	List<MotherFollowupCurrentlyOn> getMotherFollowupList(Date startDate, Date endDate, String status, Integer pregnancyOutcome, String startOrEnd);

    List<Child> getChildList();

    Child getChildById(Integer childId);

    Child saveChild(Child child);

	ChildFollowup getChildFollowupById(Integer childFollowupId);

	ChildFollowupVisit getChildFollowupVisitById(Integer childFollowupVisitId);

	ChildFollowup saveChildFollowup(ChildFollowup childFollowup);

	ChildFollowupVisit saveChildFollowupVisit(ChildFollowupVisit childFollowupVisit);

    ChildFollowupVisit getChildFollowupVisitByChildAndDate(Integer childId, Date visitDate);

	List<ChildFollowupVisit> getChildFollowupVisitByChild(Integer childId);

	void deleteChildFollowupVisit(ChildFollowupVisit childFollowupVisit);

	List<ChildFollowupTransformer> getChildFollowupList(String status, Date startDate, Date endDate);

    void deleteChildFollowup(ChildFollowup childFollowup);

    List<MotherFollowupVisit> getMotherFollowupVisitByPatientAndFollowup(MotherFollowup motherFollowup);

    Relationship getChildRelationship(Patient mother, Patient patient);

    Consultation getPatientConsultationByDate(Integer pregnantPatientId, Date consultationDate);

	List<MotherFollowupAppointment> getPregnantPatientsAppointment();
	List<MotherFollowupAppointment> getPregnantPatientsAppointmentMissed();

	List<ChildFollowupAppointment> getChildByAppointment();
	List<ChildFollowupAppointment> getChildByAppointmentMissed();

	Boolean isDead(Patient patient);
	Boolean isTransfered(Patient patient);

	/**
	 * Report service
	 */

	// Indicator
	List<ReportingIndicator> getAllIndicators();
	List<ReportingIndicator> getAllIndicators(Boolean includeVoided);
	ReportingIndicator getIndicatorById(Integer indicatorId);
	ReportingIndicator saveReportingIndicator(ReportingIndicator indicator);
	Boolean removeIndicator(Integer indicatorId);
	ReportingIndicator voidIndicator(Integer indicatorId);

	 // Template
	List<ReportingTemplate> getAllTemplate();
	List<ReportingTemplate> getAllTemplate(Boolean includeVoided);
	ReportingTemplate getTemplateById(Integer templateId);
	ReportingTemplate saveReportingTemplate(ReportingTemplate template);
	Boolean removeTemplate(Integer templateId);
	ReportingTemplate voidTemplate(Integer templateId);
	 /**

	 * End Report Service
	 */
}