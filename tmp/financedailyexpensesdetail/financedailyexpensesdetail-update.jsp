<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>编辑费用报销明细</title>
<%@ include file="../../taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${rootPath}web/assets/js/grid.utils.js?timestamp=${timestamp}"></script>
<script type="text/javascript">
  $(function(){
    $.topuiRender();
    var params = ['financeDailyExpensesDetailForm'];
    <c:if test="${fn:startsWith(model.id, \"TMP\")}">
    autoValidation(params);
    </c:if>
    <c:if test="${not fn:startsWith(model.id, \"TMP\")}">
    autoValidation(params, '${model.id}');
    </c:if>
    
    <c:if test="${not fn:startsWith(model.id, \"TMP\") && (readonly || not hasPermission)}">
    $('#financeDailyExpensesDetailForm').setDisable();
    
    disableOkBtn();
    disableDelBtn();
    </c:if>
  });
  
  
  function setFormData(){
    //采集数据转换处理
  }
</script>
</head>
<body>
  <input style="height:0;width:0;border:0;position: absolute;" /><!--光标置顶，获得激活，平台作了一个自动激活第一个Input解决弹窗需2次激活的bug-->
  <div>
    <form id="financeDailyExpensesDetailForm" style="width: 100%">
      <input type="hidden" id="rootPath" value="${rootPath}web/financeDailyExpensesDetail/"/>
      <input type="hidden" name="id" value="${model.id}"/>
      <input type="hidden" id="id" name="id" value="${model.id}"/>
      <div class="transBorder">
        <table class="formStyle">
          <tr>
            <td class="formItem">费用项目：</td>
            <td><input type="text" id="name" name="name" data-prompt-position="bottomLeft" maxlength="200" class="width_100p" value="${model.name}"/></td>
          
            <td class="formItem">金额：</td>
            <td><input type="text" id="amt" name="amt" data-prompt-position="bottomLeft" maxlength="200" class="width_100p" value="${model.amt}"/></td>
          
            <td class="formItem">附单据（张）：</td>
            <td><input type="text" id="billsNum" name="billsNum" data-prompt-position="bottomLeft" maxlength="200" class="width_100p" value="${model.billsNum}"/></td>
          </tr>
          <tr>
            <td align="left"><span class="redStar">*</span>为必填项</td>
            <td align="right" colspan="11"><span id="promptSpan" class="redStar"><c:if test="${not fn:startsWith(model.id, \"TMP\") && not readonly && not hasPermission }">您没有编辑当前数据的权限！</c:if></span></td>
          </tr>
        </table>
      </div>
      </div>
    </form>
  </div>
</body>
</html>