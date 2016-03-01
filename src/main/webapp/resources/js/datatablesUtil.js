function makeEditable() {

    $('#add').click(function () {
        $('#editRow').modal();
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    $('#sendMessageToAll').click(function() {
        $('#sendMessageToAllModal').modal();
    });
}

function sendMessage(){
    $('#sendMessage').click(function (){
        $('#sendMessageModalForm').modal();
    });
}

function editUser() {
    $('.changeAccountStatus').click(function() {
        changeAccountStatus($(this).attr("accountId"), $(this).attr("userId"));
    });
    $('#edit').click(function () {
        $('#editUserForm').modal();
    });
    $('.changeUserStatus').click(function(){
        changeUserStatus($(this).attr("userId"));
    });
    $('.changeAdminRole').click(function(){
        changeAdminRole($(this).attr("userId"));
    });
    $('#sendMessageToUser').click(function(){
       $('#sendMessageToUserModal').modal();
    });
}

function activateFilterScripts(){
    activateDateTimePicker();
    var accountHistory;
    var accountHistoryParam;

    $(function () {
        accountHistory = $('#accountHistory');
        accountHistoryParam = {
            "bPaginate": true,
            "bSort": false,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "Type"
                },
                {
                    "mData": "Your account"
                },
                {
                    "mData": "Contract's account"
                },
                {
                    "mData": "Amount"
                },
                {
                    "mData": "Balance"
                },
                {
                    "mData": "Commission"
                },
                {
                    "mData": "Operation time"
                },
                {
                    "mData": "Comment"
                }
            ]
        };
        accountHistory.dataTable(accountHistoryParam);
    });
}

function activateDateTimePicker(){
    var startDate = $('#startDate');
    var endDate = $('#endDate');
    startDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        lang: 'ru',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                maxDate: endDate.val() ? endDate.val() : false
            })
        }
    });
    endDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        lang: 'ru',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                minDate: startDate.val() ? startDate.val() : false
            })
        }
    });

    $('.time-picker').datetimepicker({
        datepicker: false,
        format: 'H:i',
        lang: 'ru'
    });

    $('#dateTime').datetimepicker({
        format: 'Y-m-d\\TH:i',
        lang: 'ru'
    });
}
var currentId;
function editAccount(){
    $('#addAccount').click(function () {
        $('#createAccount').modal();
    });
    $('.menu').click(function(){
        currentId = $(this).attr("accountId");
        $('#currentAccountId').attr("value", currentId);
        var historyHref = $('#history').attr("href").split('=')[0] + '=';
        historyHref += currentId;
        $('#history').attr("href", historyHref);
        var deleteHref = $('#deleteAccount').attr("href").split('=')[0] + '=';
        deleteHref += currentId;
        $('#deleteAccount').attr("href", deleteHref);
        $('#accountMenu').modal();
    });
    $('#rename').click(function (){
        $('#renameMenu').modal();
        $('#renameAccountId').attr("value", currentId);
    });
    $('#putMoney').click(function(){
        $('#putMoneyMenu').modal();
        $('#putMoneyAccountId').attr("value", currentId);
    });
    $('#sendMoney').click(function(){
        $('#sendMoneyMenu').modal();
        $('#sendMoneyAccountId').attr("value", currentId);
    });

}

function changeAccountStatus(accountId, userId) {
    $.ajax({
        url: accountStatus + "accountId=" + accountId + "&userId=" + userId,
        type: 'GET',
        success: function(){
            updateUserSettings(userId, 'Account status has been changed');
        }
    });
}

function changeUserStatus(userId) {
    $.ajax({
        url: userStatus + "userId=" + userId,
        type: "GET",
        success: function(){
            updateUserSettings(userId, 'User status has been changed' );
        }
    });
}


function changeAdminRole(userId) {
    $.ajax({
        url: adminRole + "userId=" + userId,
        type: 'GET',
        success: function(){
            updateUserSettings(userId, 'User role has been changed');
        }
    })
}


function updateUserSettings(userId, message){
    $.ajax({
        url: userSetting + userId,
        type: "GET",
        success: function(html){
            $("body").html(html);
            successNoty(message);
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        "text":text,
        "layout":"top",
        "type":"success",
        "textAlign":"center",
        "easing":"swing",
        "animateOpen":{
            "height":"toggle"
        },
        "animateClose": {
            "height":"toggle"
        },
        "speed":"5",
        "timeout":1000,
        "closable":true,
        "closeOnSelfClick":true
    });
}


function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
