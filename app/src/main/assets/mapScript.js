$(document).ready(function() {
    $("#my-svg").fadeIn();
    const tempHeader = $("#horario").html();
    const serverUrl = "http://escomapptest.ddns.net:1235";
    //some functions
    function getSalon(salonesArray, salon) {
        return salonesArray[salon.toString()];
    }

    function saveLocaly() {
        $.ajax({
                url: serverUrl,
                type: 'GET',
                dataType: 'json',
                data: null,
            })
            .done(function(data) {
                var clases = data.recordset;
                var actualSalon = "";
                var salones = new Object();
                for (var i = 0; i < clases.length; i++) {
                    if (clases[i].Salon != actualSalon) {
                        actualSalon = clases[i].Salon;
                        if (!salones[actualSalon.toString()])
                            salones[actualSalon.toString()] = new Array();
                    }
                    salones[actualSalon.toString()].push(clases[i]);

                }
                localStorage.salones = JSON.stringify(salones);
                console.log("Data saved locally");
            })
            .fail(function(error) {
                console.log("Get online in order to save schedules locally");
            })
            .always(function() {
                console.log("save locally complete");
            });

    }

    function showChart() {
        var topPer = (100 - ($("#horario").height() / $(window).height()) * 100) / 2;
        $("#horario").css("top", topPer + "%");
        $("#horario")
            .css("display", "flex")
            .hide()
            .fadeIn('fast');
        $("#opacity-mask").css({
            opacity: '0.7',
            display: 'inline'
        });
    }

    function chart2Message(msg) {
        $("#horario").html("<h1>" + msg + "</h1>");
        $("#horario").css({
            padding: '6px',
            textAlign: 'center',
            width: '100%'
        });
    }


    function designSchedule(horario) {

        if (horario) {
            $("#horario").html(tempHeader);
            var innerHTML = "";
            var grupoActual = horario[0].Grupo;
            innerHTML += "<tr class='grupo center'><td colspan='8'>" + grupoActual + "</td></tr>"
            for (var i = 0; i < horario.length; i++) {
                var clase = horario[i];
                if (clase.Grupo != grupoActual) {
                    grupoActual = clase.Grupo;
                    innerHTML += "<tr class='grupo center'><td colspan='8'>" + grupoActual + "</td></tr>"
                }
                innerHTML += "<tr class='clase'>";
                innerHTML += "<td>" + clase.NombreAsig + "</td>";
                innerHTML += "<td>" + clase.NombreProf + "</td>";
                innerHTML += "<td>" + clase.CveLab + "</td>";
                innerHTML += "<td>" + clase.Lunes + "</td>";
                innerHTML += "<td>" + clase.Martes + "</td>";
                innerHTML += "<td>" + clase.Miercoles + "</td>";
                innerHTML += "<td>" + clase.Jueves + "</td>";
                innerHTML += "<td>" + clase.Viernes + "</td>";
                innerHTML += "</tr>";
            }

            $("#horario").append(innerHTML);


        } else {
            chart2Message("No se ha encontrado información de este salón.");
        }
        //Chart design and display
        showChart();
    }
    //Special rooms
    function showSpecialRooms(salon) {
        let flag = false;
        let msg = "";
        switch (salon) {
            case "2108":
                msg = "Departamento de Ingeniería en Sistemas Computacionales";
                break;
            case "2107":
                msg = "Laboratorio de cómputo";
                break;
            case "2106":
                msg = "Laboratorio de sistemas 2";
                break;
            case "1008":
                msg = "Unidad Tecnológica.";
                break;
            case "1108":
                msg = "Dirección.";
                break;
            case "1109":
                msg = "Sala del Consejo Técnico Consultivo Escolar.";
                break;
            case "2208":
                msg = "Sala de profesores de Ciencias Básicas.";
                break;
            case "2201":
                msg = "Unidad Politécnica de Integración Social";
                break;
            case "1201":
                msg = "Subdirección de Servicios Educativos e Integración Social";
                break;
            case "1101":
                msg = "Unidad de Informática";
                break;
            case "1104":
                msg = "Laboratorio de Redes";
                break;
            case "1105":
                msg = "Laboratorio de Sistemas 3";
                break;
            case "1106":
                msg = "Laboratorio de Sistemas 4";
                break;
            case "1107":
                msg = "Laboratorio de Programación 2";
                break;
            case "1114":
                msg = "Laboratorio de Proyectos de Investigación";
                break;
            case "1008":
                msg = "Unidad de Tecnología Educativa y Campus Virtual";
                break;
            case "1007":
                msg = "Salón de Clex";
                break;
            case "1006":
                msg = "Salón de Celex";
                break;
            case "1005":
                msg = "Coordinación de Inglés y Centro de Lenguas Extranjeras";
                break;
            case "1004":
                msg = "Departamento de Recursos Financieros, Departamento de Recursos Materiales y Servicios, Departamento de Capital Humano";
                break;
            case "1003":
                msg = "Control de Asistencia";
                break;
            case "1002":
                msg = "Servicio Médico, Servicio Dental, Orientación Educativa, COSECOVI";
                break;
            case "1001":
                msg = "Subdirección Administrativa";
                break;
            case "1102":
                msg = "Sala de Impresiones";
                break;
            case "1103":
                msg = "Sala de Impresiones";
                break;
            case "1009":
                msg = "Sala de Trabajos Terminales";
                break;
        }
        if (msg != "") {
            chart2Message(msg);
            showChart();
            flag = true;
        }
        return flag;
    }

    $("#Prefectura").click(function() {
        chart2Message("Prefectura.");
        showChart();
    });
    $("#Jefatura").click(function() {
        chart2Message("Jefatura.");
        showChart();
    });
    $("#SubAcademica").click(function() {
        chart2Message("Subdirección Académica.");
        showChart();
    });
    $("#Minirobótica").click(function() {
        chart2Message("Club de Minirobótica.");
        showChart();
    });
    $("#PuntosReunion").click(function() {
        chart2Message("Punto de Reunión en caso de emergencia.");
        showChart();
    });
    $("#LabAnalog").click(function() {
        chart2Message("Laboratorios de Electronica Analógica/Instrumentación 1 & 2.");
        showChart();
    });
    $("#DptoIngSisComp").click(function() {
        chart2Message("Departamento de Ingeniería en Sistemas Computacionales.");
        showChart();
    });
    $("#DptoCeIngComp").click(function() {
        chart2Message("Departamento de Ciencias e Ingeniería de la Computación.");
        showChart();
    });
    $("#LabDig").click(function() {
        chart2Message("Laboratorios de asignaturas de Sistemas Digitales.");
        showChart();
    });
    $("#DptoCSocial").click(function() {
        chart2Message("Departamento de Ciencias Sociales.");
        showChart();
    });
    $("#Sala14").click(function() {
        chart2Message("Sala Eduardo Torrijos (Sala 14).");
        showChart();
    });
    $("#Lab12").click(function() {
        chart2Message("Laboratorio 12.");
        showChart();
    });
    $("#Lab24").click(function() {
        chart2Message("Sala de profesores, Laboratorio 24.");
        showChart();
    });
    $("#_23S").click(function() {
        chart2Message("Sala de profesores, 23S.");
        showChart();
    });
    $("#_26S").click(function() {
        chart2Message("Sala de profesores, 26S.");
        showChart();
    });
    //Schedule script

    $("text").css("cursor", "pointer");
    $("#opacity-mask").click(function(event) {
        $("#horario").fadeOut('fast');
        $("#horario").css('width', 'auto');
        $("#opacity-mask").css("display", "none");
    });
    $("g:regex(id, [_][1-2][0-9]+)").click(function() {
        var salon = this.id.substring(1, 5);
        if (!showSpecialRooms(salon))
            if (typeof(Storage) !== "undefined") {
                window.JavaBridge.showToast(salon);
                if (localStorage.salones)
                    designSchedule(getSalon(JSON.parse(localStorage.salones), salon));
                else {
                    $.ajax({
                            url: serverUrl,
                            type: 'GET',
                            dataType: 'json',
                            data: { salon: salon },
                        })
                        .done(function(data) {

                            var salon = data.recordset;
                            if (typeof(Storage) !== "undefined") {
                                if (!localStorage.salones) {
                                    saveLocaly();
                                    console.log("Schedules saved as localStorage with 'salones' as key");
                                }
                            } else {
                                console.log("Local storage is not available, you will only have the schedule while you are online");
                            }
                            designSchedule(salon);
                            console.log(salon);
                            console.log("Success");
                        })
                        .fail(function(error) {
                            console.log("Error: " + error);

                        })
                        .always(function() {
                            console.log("complete");
                        });
                }
            } else {
                console.log("Local storage not supported");
            }
        var d = Date();
        if (d + 1 == 2 || d + 1 == 8)
            saveLocaly();

    });
    //Launch fragment inside the menu
    $("#Biblioteca").click(function() {
        window.JavaBridge.launchLibrary();
    });
    $("#Cafeteria").click(function() {
        window.JavaBridge.launchCafe();
    });

    //Nav script
    var pisoActual = 1;
    $("#my-svg").height($(window).height() - 20);
    $("#_2doNivel").hide();
    $("#_3erNivel").hide();
    $("#BtnDown").hide();
    $("#BtnUp").click(function() {
        pisoActual++;
        $("#BtnDown").show();
        if (pisoActual === 2) {
            $("#_1erNivel").hide();
            $("#_2doNivel").show();
        } else {
            $("#BtnUp").hide();
            $("#_2doNivel").hide();
            $("#_3erNivel").show();
        }
    });
    $("#BtnDown").click(function() {
        pisoActual--;
        $("#BtnUp").show();
        if (pisoActual === 2) {
            $("#_3erNivel").hide();
            $("#_2doNivel").show();
        } else {
            $("#BtnDown").hide();
            $("#_2doNivel").hide();
            $("#_1erNivel").show();
        }
    });

});