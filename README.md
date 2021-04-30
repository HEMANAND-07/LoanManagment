# LoanManagment
A Simple Loan Management REST API design 


@GET 
CUSTOMER HISTRY 
http://localhost:8080/loanmanagement/webapi/admin/customers-history
get payment history
http://localhost:8080/loanmanagement/webapi/admin/payments-history
loanhistory 
http://localhost:8080/loanmanagement/webapi/admin/loans-history
view a particular loan 
http://localhost:8080/loanmanagement/webapi/admin/view-loan/4


@POST
provide loan call 
http://localhost:8080/loanmanagement/webapi/admin/Approve-Loan
{
        "cust": {
            "customerAddress": "92/A TUTY",
            "customerId": 1,
            "customerMobile": "7339437399",
            "customerName": "ramji",
            "customerPan": "AJHG123J23",
            "deptAmt": 11000
        },
        "dateOfDisbursement": "2021-04-30",
        "loanAmount": 11000,
        "loanId": 1,
        "loanIntrest": 10.0,
        "loanTenure": 10,
        "loanType": "personal"
    }
RECORD PAYMENT
http://localhost:8080/loanmanagement/webapi/admin/record-payment
{
    "loanId":1,
    "custId":1,
    "amtPaid":1100,
    "notes":"paid as cash"
}

@PUT
update address call
http://localhost:8080/loanmanagement/webapi/admin/UpdateCustAcc

@DELETE
delete a customer record
http://localhost:8080/loanmanagement/webapi/admin/del-cust/1
