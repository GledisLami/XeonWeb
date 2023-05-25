console.log('running proceset');


let trueProjectId;
start();

function start() {
    let projectName = document.querySelector("#project-description");
    if (projectName) {
        trueProjectId = localStorage.getItem('projectId')
        projectName.innerHTML = `<br><br/><center> <h2>Projekti: ${trueProjectId} </h2></center>`;

        displayProcesses(trueProjectId);
    }
}


//Logjika e butonit shto
let shto = document.getElementById("shto");
shto.addEventListener('click', () => {
    event.preventDefault(); //Per te shmangur auto reload
    var machineryType = document.getElementById("machinery-type").value;
    var processName = document.getElementById("process-name").value;
    var quantity = document.getElementById("quantity").value;
    var koha = document.getElementById("koha").value;
    var pershkrimi = document.getElementById("text-description").value;
    ;


    $.ajax({
        type: "POST", //USER ID DUHET NDRYSHUAR
        url: 'http://localhost:8090/xoen/inxhinieri/proceset/saveTest?userId=3&projektiId=' + trueProjectId + '&pershkrimi=' + pershkrimi + '&koha=' + koha + '&makineria=' + machineryType + '&sasia=' + quantity + '&tipiProcesit=' + processName,
        dataType: 'json',
        cache: true,
        success: function (html) {
            console.log(html);
            showModal(html);
            start();
        },
        error: function (errMsg) {
            console.log(errMsg);
            showModal('Procesi u shtua me sukses!!');
            start();
            // showModal('ERROR! '+errMsg);
        }

    });


});

//Nese duam te nryshojme sasine
let quantityInputs = document.querySelectorAll('#quantity');

for (let i = 0; i < quantityInputs.length; i++) {
    quantityInputs[i].addEventListener('change', () => {
        quantityChanged(event, i);
    });
}

//Nese duam te nryshojme kohen
let timeInputs = document.querySelectorAll('#koha');

for (let i = 0; i < timeInputs.length; i++) {
    timeInputs[i].addEventListener('change', () => {
        //console.log('produkti me index: ',i);

        quantityChanged(event, i);
    });
}


function quantityChanged(event, i) {

    var input = event.target;
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 1;
    }
    start();


}


function displayProcesses(projectId){
     
     $.ajax({
        type:"GET",
        url:"http://localhost:8090/xoen/inxhinieri/proceset?projektiId="+projectId, // endpoint-i  localhost:8090/xoen/financa
        dataType: 'json',
        cache: true,
        success: function(html){
            let projectItems=html;
            let productContainer =document.querySelector(".products");
          
           //kontrollojm nese kemi produkte ne localStorage dhe vendosim nje specifik qe ky if do te
           //aksesohet vetem nese jemi ne cart Page sepse po i referohemi nje elmenti html te kesaj faqeje
          if(productContainer){
                let i=0;
                productContainer.innerHTML='';
                Object.values(projectItems).map(item =>{
                    let status= (item.fazaId==1)? 'S ka filluar' : (item.fazaId==2)? 'Duke punuar' :'Ka mbaruar';

                    productContainer.innerHTML += ` 
                    <tr>
                            <td>${item.makineriaId}</td>
                            <td>${item.tipiProcesitId}</td>
                            <td>${item.id}</td>
                            <td>${item.koha}</td>
                            <td>${item.sasia}</td>
                            <td>${status}</td>
                           
                            <!--<td><button class="normal confirm" id="${i}">edit</button></td>-->
                            <td><button class="normal reject" id="${i}">delete</button></td>
                            
                        </tr>
                    `;
                    i++;

                });


                
                             //Nese duam t i bejm edit nje projecti per momentin s po e perdorim
                            let confirmButtons = document.querySelectorAll('.confirm');
                            for (let i = 0; i < confirmButtons.length; i++){
                                confirmButtons[i].addEventListener('click',()=>{
                                    console.log('dua te shfaq ',i);
                                   // editProject(projectItems,i);
                                    
                                });     
                            }

                            //Nese duam t i bejme delete nje projeti
                                let rejectButtons = document.querySelectorAll('.reject');
                            for (let i = 0; i < rejectButtons.length; i++){
                                rejectButtons[i].addEventListener('click',()=>{
                                    console.log('dua te refuzoj',i);
                                    deleteProject(projectItems,i);
                                   
                                });     
                            }

                        }
                 
        
        },      
        error: function (errMsg){
                console.log('ka error');
            }

        });
    
   


}


function deleteProject(projectItems,i){
    
    $.ajax({
        method:"DELETE",
        url:"http://localhost:8090/xoen/inxhinieri/proceset/delete?procesiId="+projectItems[i].id,  // endpoint-i  /xoen/financa/delete
        cache: true,
        success: function(html){
            console.log("Morem nga db");
            showModal(html);
            start();
        },
        error: function(errMsg){
            showModal(errMsg);
        }

    });
    
}



function showModal(data){  
    var modal = document.getElementById("myModal2");
    var span1 = document.getElementsByClassName("close1")[0];

    // When the user clicks on the button, open the modal
    modal.style.display = "block";
    let modalDescription =document.querySelector("#modal-description");
    if(modalDescription){
        
        modalDescription.innerHTML= `${data}`;
    }
    // When the user clicks on <span> (x), close the modal
    span1.onclick = function () {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            //console.log("shtype jasht kutis");
            modal.style.display = "none";
        }
    }

}

function getFinanceProject2 (){
 
    var proceset=[{
        makineriaId: 'Sharra',
        tipiProcesitId: 'prerje',
        id_procesi: '1',
        koha: '-',  
        sasia: 40,
      
    },
    {
        makineriaId: 'Freze',
        tipiProcesitId: 'frezim',
        id: '2',
        koha: '-',   
        sasia: 20,
        faza: 'Filluar'
    },{
        makineriaId: 'Sharra',
        tipiProcesitId: 'prerje',
        id: '3',
        koha: '-',  
        sasia: 40,
        faza: 'Filluar'
    },
    {
        makineriaId: 'Freze',
        tipiProcesitId: 'frezim',
        id: '4',
        koha: '-',   
        sasia: 20,
        faza: 'Filluar'
    },{
        makineriaId: 'Sharra',
        tipiProcesitId: 'prerje',
        id: '5',
        koha: '-',  
        sasia: 40,
        faza: 'Filluar'
    },
    {
        makineriaId: 'Freze',
        tipiProcesitId: 'frezim',
        id: '6',
        koha: '-',   
        sasia: 20,
        faza: 'Filluar'
    }];
   
    return proceset;
  
}

