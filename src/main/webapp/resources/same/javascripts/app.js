PrimeFaces.locales['pt'] = {
	closeText : 'Fechar',
	prevText : 'Anterior',
	nextText : 'Próximo',
	currentText : 'Começo',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
			'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago',
			'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta',
			'Sábado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
	dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	weekHeader : 'Semana',
	firstDay : 0,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Só Horas',
	timeText : 'Tempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	ampm : false,
	month : 'Mês',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Todo o Dia'
};

function deleteItem(itemID) {
	
	const swalWithBootstrapButtons = swal.mixin({
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: false,
		})

		swalWithBootstrapButtons({
		  title: 'Tem certeza?',
		  text: "O registro será apagado do seu banco de dados!",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Sim, delete!',
		  cancelButtonText: 'Não, cancele!',
		  reverseButtons: true
		}).then((result) => {
		  if (result.value) {
			  
			  setarItemID([ {
					name : 'itemID',
					value : itemID
				} ]);
			  		 
		  }
		})
		
}

function cancelarVenda() {
	
	const swalWithBootstrapButtons = swal.mixin({
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: false,
		})

		swalWithBootstrapButtons({
		  title: 'Tem certeza?',
		  text: "A venda será cancelada e os itens serão removidos!",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Sim, confirmar!',
		  cancelButtonText: 'Não, cancele!',
		  reverseButtons: true
		}).then((result) => {
		  if (result.value) {
			  
			  cancelaVenda();
			  		 
		  }
		})
		
}

function estornarConta(itemID) {
	
	const swalWithBootstrapButtons = swal.mixin({
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: false,
		})

		swalWithBootstrapButtons({
		  title: 'Atenção!',
		  text: "Deseja realmente desfazer o pagamento?",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Sim, estorne!',
		  cancelButtonText: 'Não, cancele!',
		  reverseButtons: true
		}).then((result) => {
		  if (result.value) {
			  
			  setarItemID([ {
					name : 'itemID',
					value : itemID
				} ]);
			  		 
		  }
		})
		
}

function atualizaPedido() {
	
	let timerInterval;
	Swal.fire({
	  type: 'warning',
	  title: 'Atualizando',
	  html: 'Tente novamente em aguns instantes, estamos atualizando seu pedido.<br/><b></b>',
	  timer: 5000,
	  allowOutsideClick: false,
	  timerProgressBar: true,
	  onBeforeOpen: () => {
	    Swal.showLoading()
	    timerInterval = setInterval(() => {
	      
	    }, 100)
	  },
	  onClose: () => {
	    clearInterval(timerInterval)
	  }
	}).then((result) => {
	  /* Read more about handling dismissals below */
	  if (result.dismiss === Swal.DismissReason.timer) {
		  $(location).attr('href','pagamento.xhtml');
	  }
	})
}

function pedidoEnviado() {
	
	let timerInterval;
	Swal.fire({
	  type: 'success',
	  title: 'Pagamento realizado!',
	  html: 'Obrigado, em breve entraremos em contato, redicionando para a página principal.<br/><b></b>',
	  timer: 10000,
	  allowOutsideClick: false,
	  timerProgressBar: true,
	  onBeforeOpen: () => {
	    Swal.showLoading()
	    timerInterval = setInterval(() => {
	      
	    }, 100)
	  },
	  onClose: () => {
	    clearInterval(timerInterval)
	  }
	}).then((result) => {
	  /* Read more about handling dismissals below */
	  if (result.dismiss === Swal.DismissReason.timer) {
	    console.log('I was closed by the timer');
	    home();
	  }
	})
}

function guessPaymentMethod(event) {
    let cardnumber = document.getElementById("cardNumber").value;

    if (cardnumber.length >= 6) {
        let bin = cardnumber.substring(0,6);
        window.Mercadopago.getPaymentMethod({
            "bin": bin
        }, setPaymentMethod);
    }
};

function setPaymentMethod(status, response) {
    if (status == 200) {
    	console.log(response);
        let paymentMethodId = response[0].id;
        $('.payment_method_id').val(paymentMethodId);
        getInstallments();
    } else {
        alert(`payment method info error: ${response}`);
    }
}

function getInstallments(){
    window.Mercadopago.getInstallments({
        "payment_method_id": $('.payment_method_id').val(),
        "amount": parseFloat($('.transaction_amount').val())

    }, function (status, response) {
        if (status == 200) {
        	console.log(response);
            document.getElementById('installments').options.length = 0;
            response[0].payer_costs.forEach( installment => {
                let opt = document.createElement('option');
                opt.text = installment.recommended_message;
                opt.value = installment.installments;
                document.getElementById('installments').appendChild(opt);
            });
            
            $('.installments').val(response[0].payer_costs[0].installments);
        } else {
            alert(`installments method info error: ${response}`);
        }
    });
}

function doPay_(){ 

	//var $button = document.querySelector('.mercadopago-button');
	var $anchor = document.querySelector('.mercadopago-link');
	
	stop__();

	$anchor.click();
	//$button.click();
	
	return false;

};

function doPay(){ 

	var $form = document.querySelector('#form');
	
	window.Mercadopago.createToken($form, sdkResponseHandler);
	
	return false;

};

function sdkResponseHandler(status, response) {
    if (status != 200 && status != 201) {
        alert("verify filled data");
        stop__();
    }else{
    	$('.token').val(response.id);
        
        submitForm([ {
			name : 'token',
			value : response.id
		} ]);
    }
};

var licence = "Afju1Vh1R6uYCcj5Qiftq5pEmkDUPvSK2kfKWn9N0Be5XcKHK0DL0hpzxLnRAXYaggjxaGZSMDutM6IQ7GtRENZiGKifZggPJkIfV7Vp30yCD2JgIxXD/3gbaFk/RrfcN2FhXPh82yHWCMUvnrWQVkDl1Ht1fTKgmAz5I8VkASUcZUKt5vH7AJa/stbg7++Mb7YUIgXmPIg6ltpv2G4l5NZ0ZTAopu4esra2zYQpbpkQSOXZ0CpmRkA3NaZYFyXPKf6dDFg7zx+Ir/ggFOjlMlWymdFs9Num06HPFXmMZ36g6tnZspJkWZKjAOlL3U7wJNqILHUkVCcZEG8XgJRHIIvn8hSz28Wluag9r4VEtqytQ3XFF9T15FXbmnNX5NExTknP6Ya0Ag/jk1xw6zY1RsBXBVmCym3Nb3/xDJsgaxEGfTSXdRcadW5W2g0TAKUX4uAuu6NQJgbEOrEDsXCRvlEQCkNuX1HIBRK/RtoI8RQaMk7c+OIHmaLvvmDkl7VMfVbpVeOhSeixuSFnbZDdOD53uU4S0izncMsLa4Udv3T00fj+cHTHRNtz++7nY17rTvity8b2K/tfe1SlbY7+3p8M+ULfx8fS1TPBqKqqc4dj5QLkt5H+/dFw4a+y7cY2NGtTtuFH8Bod2pzezgZoZPOReJPzwt8ODUMoFb/LyWD3wWXrig/tHBdLa2fLz6gqOox4vqtJDMexLW034GX2UtE3I11YuAvtgHiMxe/ipmn+Gsrx8RHusdDVaLRgAWHuNK+9RE3OmeLrt/gjVYUDEfRVht55tCEErzbhjjWE";

ScanditSDK.configure(licence, {
	  engineLocation: "https://cdn.jsdelivr.net/npm/scandit-sdk@5.x/build/",
	})
	  .then(() => {
	    return ScanditSDK.BarcodePicker.create(document.getElementById("scandit-barcode-picker"), {
	      // enable some common symbologies
	      scanSettings: new ScanditSDK.ScanSettings({ enabledSymbologies: ["ean8", "ean13", "upca", "upce"] }),
	    });
	  })
	  .then((barcodePicker) => {
	    // barcodePicker is ready here, show a message every time a barcode is scanned
	    barcodePicker.on("scan", (scanResult) => {
	      alert(scanResult.barcodes[0].data);
	    });
});
