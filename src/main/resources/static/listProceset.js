console.log('running listProceset');
start();
function start(){
    let projectName =document.querySelector("#project-description");
    if(projectName){
            let projectId= localStorage.getItem('projectId');
            projectName.innerHTML= `<br><br/><center> <h2>Projekti: ${projectId} </h2></center>`;

            displayProcesses(projectId);
    }

}


function displayProcesses(projectId){
    
    $.ajax({
       type:"GET",
       url:"http://localhost:8090/xoen/operatori/proceset?projektiId="+projectId, // endpoint-i  localhost:8090/xoen/financa
       dataType: 'json',
       cache: true,
       success: function(html){
                   console.log("Morem nga db");
           let projectItems=html;
           let productContainer =document.querySelector(".products");
         
         if(productContainer){
               let i=0;
               productContainer.innerHTML='';
               Object.values(projectItems).map(item =>{
                
                let status= (item.fazaId==1)? 'S ka filluar' : (item.fazaId==2)? 'Duke punuar' :'Ka mbaruar';
                let start= (item.fazaId==1)? 1:0; //1 enable
                let end=(item.fazaId==2)?1:0; //1 enable

                let startDisable=(start==1)? ' ' : 'disabled';
                let endDisable=(end==1)?' ' : 'disabled';
                productContainer.innerHTML += ` 
                <tr>
                        <td>${getMachineType(item.makineriaId)}</td>
                        <td>${getProcesiType(item.tipiProcesitId)}</td>
                        <td>${item.sasia}</td>
                        <td><button class="normal info" >Te dhenat teknike</button></td>
                        <td>${status}</td>
                        <td><button class="normal confirm" ${startDisable} >Fillo</buton></td>
                        <td><button class="normal reject" ${endDisable}>Mbaro</button></td>
                    </tr>
                `;  
                   i++;

               });

                             //Nese duam te shfaqim pershkrimin e nje projecti
                           let infoButtons = document.querySelectorAll('.info');
                           for (let i = 0; i < infoButtons.length; i++){
                                infoButtons[i].addEventListener('click',()=>{
                                   console.log('dua t i bej info ',i);
                                   showModal(projectItems[i].pershkrimi);
                                   
                               });     
                           }
               
                            //Nese duam t i bejm start nje procesi
                           let confirmButtons = document.querySelectorAll('.confirm');
                           for (let i = 0; i < confirmButtons.length; i++){
                               confirmButtons[i].addEventListener('click',()=>{
                                    if(projectItems[i].fazaId==1){
                                    console.log('dua t i bej start ',i);
                                    startProcess(projectItems,i);
                                    }else{
                                        console.log('eshte disabled')
                                    }
                                   
                               });     
                           }

                           //Nese duam t i bejme stop nje procesi
                               let rejectButtons = document.querySelectorAll('.reject');
                           for (let i = 0; i < rejectButtons.length; i++){
                               rejectButtons[i].addEventListener('click',()=>{
                                  if(projectItems[i].fazaId==2){
                                   console.log('dua te refuzoj',i);
                                   stopProcess(projectItems,i);
                                  }else{
                                        console.log('eshte disabled')
                                    }
                               });     
                           }

                       }
                
       },      
       error: function (errMsg){
               console.log('ka error');
           }

       });
   
  


   }

   function infoProcess(projectItems,i){
    
    $.ajax({
        method:"GET",
        url:"http://localhost:8090/xoen/financa/delete?id="+projectItems[i].id,  // endpoint-i  /xoen/financa/delete
        //data:check,
        //dataType: 'json',
        cache: true,
        success: function(html){
            console.log("Morem nga db");
            alert(html);//nese ka ndonje error ne php del ketu
            start();
        },
        error: function(errMsg){
            console.log(errMsg);
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

function startProcess(projectItems,i){
   console.log(projectItems[i].id);
    $.ajax({
        method:"PUT",
        url:" http://localhost:8090/xoen/operatori/proceset/nis?procesiId="+projectItems[i].id,  // endpoint-i  /xoen/financa/delete
        //data:check,
        //dataType: 'json',
        cache: true,
        success: function(html){
            console.log("Morem nga db");
            showModal(html);//nese ka ndonje error ne php del ketu
            start();
        },
        error: function(errMsg){
            console.log(errMsg);
        }

    });
    
}

function stopProcess(projectItems,i){
    
    $.ajax({
        method:"PUT",
        url:"http://localhost:8090/xoen/operatori/proceset/perfundo?procesiId="+projectItems[i].id,  // endpoint-i  /xoen/financa/delete
        //data:check,
        //dataType: 'json',
        cache: true,
        success: function(html){
            console.log("Morem nga db");
            start();
        },
        error: function(errMsg){
            console.log(errMsg);
        }

    });
    
}


function getMachineType(i){
    switch(i) {
        case 1:
            return 'Machine 1';
        case 2:
            return 'Machine 2';
        case 3:
            return 'Machine 3';
        case 4:
            return 'Machine 4';
        case 5:
            return 'Machine 5';
        default:
           return 'Nje proces i ri shtoje ne js'
      }
}

function getProcesiType(i){
    switch(i) {
        case 1:
            return 'Torno';      
        case 2:
            return 'Freze';
        case 3:
            return 'Prerje';
        case 4:
            return 'Kontroll Cilesie';
        case 5:
            return 'Pastrim';
      
        default:
           return 'Nje proces i ri shtoje ne js'
       
      }
}

function getFinanceProject2 (){
 
   
    var proceset=[{
        makineria: 'Sharra',
        procesi: 'prerje',
        koha: '-',  
        sasia: 40,
        statusi: 0,
        description:'shume mire'
    },
    {
        makineria: 'Freze',
        procesi: 'frezim',
        koha: '-',   
        sasia: 20,
        statusi: 1,
        description:' mire'
    },
    {
        makineria: 'Freze',
        procesi: 'frezim',
        koha: '-',   
        sasia: 20,
        statusi: 2,
        description:'shume '
    },
    {
        makineria: 'Freze',
        procesi: 'frezim',
        koha: '-',   
        sasia: 20,
        statusi: 0,

    },
    {
        makineria: 'Freze',
        procesi: 'frezim',
        koha: '-',   
        sasia: 20,
        statusi: 0,

    },
    {
        makineria: 'Freze',
        procesi: 'frezim',
        koha: '-',   
        sasia: 20,
        statusi: 0,

    }];
   
    return proceset;
  
  
}

