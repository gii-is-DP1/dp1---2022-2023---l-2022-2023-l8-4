<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="hrefConfirm" required="true" %>
<%@ attribute name="nameModal" required="true" %>


<!-- Modal -->
<div id="${nameModal}" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are you sure you want to delete this profile?</h4>
      </div>
      <div class="modal-body">
        <p>If you delete this profile you won`t be able to return back</p>
      </div>
       <div class=dataPlayer-buttons>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a href="${hrefConfirm}"><button type="button" class="btn btn-default">Delete</button></a>
       </div>
    </div>

  </div>
</div>