<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 回复问题界面 -->
<div class="pageContent">
	<form  method="post" action="${ctx}/manager/question!replyQuestion.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			 <input type="hidden" name="pageNum" value="${pageNum}" />
	         <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>提问题姓名：</label>
				<input type="hidden" name="questionId" value="${question.id}"/>
				${question.zunmiUser.userName }
			</p>
			<p>
				<label>提问时间：</label>
				<!--  
				<input type="hidden" name="question.questionTime " value="${question.questionTime }"/>
				-->
				${question.questionTime }
			</p>
			<p>
				<label>问题题目：</label>
				<!-- 
				<input type="hidden" name="question.question  " value="${question.question  }"/>
				-->
				${question.question }
			</p>
			<p>
				<label>问题内容：</label>
				<p>${question.description }</p>
			</p>
			<p>
				<label>回复内容：</label>
				<textarea rows="20" cols="73" name="answer">${question.answer }</textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
								<button type="submit">
								保存
								</button>
						</div>
					 </div>
					</li>
				<li>
					<div class="button">
					     <div class="buttonContent">
					           <button type="button" class="close">
					                                     取消
					            </button>
					      </div>
					  </div>
				</li>
			</ul>
		</div>
	</form>
</div>








