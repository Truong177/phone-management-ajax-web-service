$(document).ready(function () {
    $('#add-smartphone').on('submit', function (event) {
        event.preventDefault(); // Chặn hành vi mặc định của form submit
        addNewSmartPhone();
    });
});

function addNewSmartPhone() {
    let producer = $('#producer').val();
    let model = $('#model').val();
    let price = $('#price').val();
    let newSmartphone = {
        producer: producer,
        model: model,
        price: price
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newSmartphone),
        url: "http://localhost:8080/api/smartphones",
        success: function () {
            // Làm sạch form sau khi thêm sản phẩm
            $('#producer').val('');
            $('#model').val('');
            $('#price').val('');
            successHandler();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error adding smartphone:", textStatus, errorThrown);
        }
    });
}

function successHandler() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/smartphones",
        success: function (data) {
            let content = '<table id="display-list" border="1"><tr>' +
                '<th>Producer</th>' +
                '<th>Model</th>' +
                '<th>Price</th>' +
                '<th>Actions</th>' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += getSmartphone(data[i]);
            }
            content += "</table>";
            $('#smartphoneList').html(content).show();
            $('#add-smartphone').hide();
            $('#display-create').show();
            $('#title').show();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error fetching smartphone list:", textStatus, errorThrown);
        }
    });
}

function displayFormCreate() {
    $('#smartphoneList').hide();
    $('#add-smartphone').show();
    $('#display-create').hide();
    $('#title').hide();
}

function getSmartphone(smartphone) {
    return `<tr><td>${smartphone.producer}</td><td>${smartphone.model}</td><td>${smartphone.price}</td>` +
        `<td class="btn"><button class="deleteSmartphone" onclick="deleteSmartphone(${smartphone.id})">Delete</button>` +
        `<button class="editSmartphone" onclick="editSmartphone(${smartphone.id})">Edit</button></td></tr>`;
}

function deleteSmartphone(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: successHandler,
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error deleting smartphone:", textStatus, errorThrown);
        }
    });
}

function editSmartphone(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: function (smartphone) {
            $('#producer').val(smartphone.producer);
            $('#model').val(smartphone.model);
            $('#price').val(smartphone.price);
            $('#add-smartphone').off('submit').on('submit', function (event) {
                event.preventDefault();
                updateSmartphone(id);
            });
            displayFormCreate();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error fetching smartphone:", textStatus, errorThrown);
        }
    });
}

function updateSmartphone(id) {
    let producer = $('#producer').val();
    let model = $('#model').val();
    let price = $('#price').val();
    let updatedSmartphone = {
        id: id,
        producer: producer,
        model: model,
        price: price
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(updatedSmartphone),
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: function () {
            $('#producer').val('');
            $('#model').val('');
            $('#price').val('');
            $('#add-smartphone').off('submit').on('submit', function (event) {
                event.preventDefault();
                addNewSmartPhone();
            });
            successHandler();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error updating smartphone:", textStatus, errorThrown);
        }
    });
}
