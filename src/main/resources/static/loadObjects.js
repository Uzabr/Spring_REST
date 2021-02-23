function loadObjects() {
    $.ajax( {
        url: "/onetooneunidiractional/mock/findAll",
        type: "GET",
        data:{},
        dataType : "json",
        success: function (data, textStatus, jqXHR) {
            processResponseData(data);
        }, error : function (jqXHR, textStatus, errorThrown) {
            document.getElementById("bookname").value="";
            alert("Error Status Load Object " + textStatus);
        }
    });
    return false;
}

function processResponseData(responseData) {
    var dynamicTableRow ="<table border='1'>"+
        +"<tr>"+
        +"<td> BookName</td>"+"<td>Shipping City</td>"+
        +"<td>Actions</td>"+
        +"<tr>";

    var dataRow = "";

    $.each(responseData, function (itemno, itemvalue) {
        dataRow=dataRow + generateTableData(itemvalue);
    });
    dynamicTableRow = dynamicTableRow + dataRow+"</table>";
    document.getElementById("bookFromResponse").innerHTML=dynamicTableRow;
}
function generateTableData(itemvalue) {
    var datRow ="<tr>"+
        "<td>"+itemvalue.name+"</td>"+
        "<td>"+itemvalue.city+"</td>"+
        "<td>"+ "<a href=# onclick='deleteObject("+ itemvalue.id +")'>Delete</a>"+
        "|<a href=# onclick='editObject('"+itemvalue.id+">Edit</a>"+
        "</td>"
        +"</tr>";
    return datRow;
}

function methodCall() {
    var buttonValue = document.getElementById("subButton").value;

    if (buttonValue=="Create") {
        create();
    }
    else if (buttonValue=="Update") {
        update();
    }
    return false;
}
function create() {
    var bname = $("#bookname").val();
    var bcity = $("#city").val();
    var formData = {bname: "name", bcity:"city"};

    $.ajax({
        url: "/onetooneunidiractional/mock/create",
        type: "POST",
        data: JSON.stringify(formData),
        beforSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data, textStatus, jqXHR) {
            document.getElementById("bookname").value="";
            document.getElementById("city").value="";
            document.getElementById("subButton").value="Create";
            loadObjects();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            document.getElementById("bookname").value="";
            alert("Error Status Create " + textStatus);
        }
    });
    return false;
}

function update() {
    var name = $("#bookname").val();
    var id = +$("#bookId").val();
    var  city = $("#city").val();

    var formData = {id:"id", name:"name", city:"city"};

    $.ajax({
        url: "/onetooneunidiractional/mock/edit",
        type : "POST",
        data: JSON.stringify(formData),

        beforSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data, textStatus, jqXHR) {
            document.getElementById("bookname").value="";
            document.getElementById("city").value="";
            document.getElementById("subButton").value="Create";
            loadObjects();
        },

        error: function (jqXHR, testStatus, errorThrown) {
            document.getElementById("bookname").value="";
            alert("Error Status Update " + testStatus);
        }
    });

    return false;
}

function editObject(bookId) {
    editUrl ="/onetooneunidiractional/mock/findById/" + bookId;

    var bookForm = {id:bookId};

    $.ajax({
        url : editUrl,
        type : "GET",
        data : bookForm,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            viewObject(data);
            document.getElementById("subButton").value="Update";
        },
        error : function (jqXHR, textStatus, errorThrown) {
            alert("Error Status Find Object" + textStatus);
        },
    });
}
function viewObject(data) {
    document.getElementById("bookname").value=data.name;
    document.getElementById("city").value=data.city;
    document.getElementById("bookId").value=data.id;
}

function deleteObject(bookId) {
    var bookForm = {id:bookId};

    delurl = "/onetooneunidiractional/mock/remove/"+bookId;

    $.ajax({
        url:delurl,
        type : "POST",
        data : bookForm,
        dataType : "json",

        success : function (data, textStatus, jqXHR) {
            loadObjects();
        },
        error : function (jqXHR, textStatus, errorThrown) {
            alert("Error Status Delete" + textStatus);
        }
    });
}
