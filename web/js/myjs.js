$(document).ready(function() {
    console.log('ready');
    init();
});

function init(){
   onshowinfo();
}

function onshowinfo(){
            $('#modelAcabado').on('show.bs.modal', function (event) {
            console.log('Evento show modal activado');
            var button = $(event.relatedTarget) // Button that triggered the modal
            var nombre = button.data('nombre');
            var id = button.data('id');
            var modal = $(this);
            modal.find('.modal-header h4').text(nombre);
            $.ajax({
                type: "GET",
                url: "Controller?op=acabado&modeloid="+id,
                success : function(info) {
                        modal.find(".modal-body").html(info);
                }
            })
	})
}
