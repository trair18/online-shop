<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>

<jsp:include page="/jsp/include/navbar.jsp" />

<div class="container-fluid">

    <form action="${pageContext.request.contextPath}/order/makeOrder" method="POST">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputAddress">Address</label>
                <input name="address" type="address" class="form-control" id="inputAddress" placeholder="Address">
            </div>
            <div class="form-group col-md-6">
                <label for="payment">Payment</label>
                <select name="payment" id="payment" type="payment" class="custom-select">
                    <option selected>Select payment type</option>
                    <option value="cash">cash</option>
                    <option value="account">account</option>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Заказать</button>
    </form>



</div>

<jsp:include page="/jsp/include/footer.jsp" />
</body>
</html>