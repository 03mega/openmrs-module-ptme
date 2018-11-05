<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage PTME Reports" otherwise="/login.htm" redirect="/module/ptme/reportTemplate.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<%@ include file="template/reportHeader.jsp"%>

<script type="application/javascript">
    if (jQuery) {
        $j(document).ready(function () {
            $j("#list-register").dataTable({
                "bPaginate": true,
                "order": [[1, "desc"]],
                "iDisplayLength": 20,
                "bLengthChange": false,
                "bFilter": true,
                "bSort": true,
                "bInfo": true,
                "bJQueryUI": true,
                "oLanguage": {
                    "oPaginate": {
                        "sPrevious": "Pr&eacute;c&eacute;dent",
                        "sNext": "Suivant"
                    },
                    "sZeroRecords": "Aucun enregistrement trouv&eacute;",
                    "sInfo": "Affichage de _START_ a _END_ sur _TOTAL_ ",
                    "sSearch": "Chercher"
                },
                "aaSorting": [[0, "desc"]]
            });
        });
    }
</script>


<c:if test="${mode == 'list' || empty(mode)}">
    <div class="box">
        <h3><b>Gestion des templates</b></h3>

        <div class="line"></div>
        <form:form action="" commandName="getTemplateFromFrom" id="form" method="get" >
            <table cellspacing="0" cellpadding="5">
                <tr>
                    <td><input type="submit" value="Nouveau" name="add"></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div class="boxHeader"></div>
    <div class="box">
        <h3>Liste des templates</h3>
        <div class="line"></div>
        <table width="100%" style="border: solid #1aac9b 1px" cellpadding="0" cellspacing="0" id="list-template">
            <thead>
            <tr style="background-color: #1aac9b; color: #ffffff;">
                <th>Nom</th>
                <th>Description</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="template" items="${ template }">
                <tr>
                    <td>${template.name}</td>
                    <td>${template.description}</td>
                    <td width="30">
                        <table cellpadding="0" cellspacing="0" class="button-table">
                            <tr>
                                <td>
                                    <c:url value="/module/ptme/reportTemplate.form" var="url">
                                        <c:param name="templateId" value="${template.templateId}"/>
                                    </c:url>
                                    <a href="${ url }"><img src="/openmrs/images/edit.gif" alt="Editer"></a>
                                </td>
                                <td>|</td>
                                <td>
                                    <c:url value="/module/ptme/reportTemplate.form" var="urlsup">
                                        <c:param name="delId" value="${template.templateId}"/>
                                    </c:url>
                                    <a href="${ urlsup }" onclick="return confirm('Voulez-vous vraiment supprimer la ligne ?');">
                                        <img src="/openmrs/images/trash.gif" alt="Supprimer">
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</c:if>

<c:if test="${mode == 'form'}">
    <div class="box">
        <h3><b>Saisie des templates</b></h3>
        <div class="line"></div>
        <form:form action="" commandName="templateForm" id="form" method="post" enctype="multipart/form-data" >
            <form:hidden path="templateId"/>
            <table cellspacing="0" cellpadding="5" align="" width="70%">
                <tr>
                    <td>
                        <table cellpadding="5" cellspacing="0" width="100%">
                            <tr>
                                <td class="boldText">Nom <b class="required">*</b> : </td>
                                <td><form:input path="name" size="80" cssClass=""/></td>
                                <td><form:errors cssClass="error" path="name"/></td>
                            </tr>
                            <tr>
                                <td class="boldText">Description : </td>
                                <td><form:textarea path="description" rows="5" cssClass="textarea-c" /></td>
                                <td><form:errors cssClass="error" path="description"/></td>
                            </tr>
                            <tr>
                                <td class="boldText">fichier <b class="required">*</b> : </td>
                                <td><input type="file" name="file"  path="filePath" size="50" cssClass=""/></td>
                                <td><form:errors cssClass="error" path="filePath"/></td>
                                <%--<td><input type="submit" value="upload"/></td>--%>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <div class="line"></div>
            <table cellspacing="0" cellpadding="5">
                <tr>
                    <td>
                        <c:if test="${ empty templateForm.templateId }">
                            <input type="submit" value="Enregistrer" name="action"/>
                        </c:if>
                        <c:if test="${ not empty templateForm.templateId }">
                            <input type="submit" value="Modifier" name="action"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</c:if>


<%@ include file="template/localFooter.jsp"%>