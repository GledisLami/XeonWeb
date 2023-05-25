console.log('running financa');
displayCart();


let shto = document.getElementById("shto");
shto.addEventListener('click', () => {
    console.log('dua te shtoj projecttt');
    var name = document.forms.RegForm.Name.value;
    var project = document.forms.RegForm.project.value;
    console.log(name);

    //Bejme validime per id e projectit 
     if (project == "" ) {
        var error = document.getElementById("projectError");
        error.textContent = "Ju lutem vendosni nje pershkrim per projektin";
        error.style.color = "red";
        error.style.fontSize = "12";
        document.forms.RegForm.project.focus();
        console.log('jo ol');
        return;
        } 
        
    else {
        var error = document.getElementById("projectError");
        error.textContent = "";
         }
         

         //Dergimi ne back
          console.log("http://localhost:8090/xoen/financa/saveTest?userId="+name+"&comments="+project);
    var porosia={userId:name , comments: project};// ruhet id e projectit te ri
    $.ajax({
        type:"POST",
        url:"http://localhost:8090/xoen/financa/saveTest?userId="+name+"&comments="+project, //?action=next // endpoint-i
        dataType: 'json',
        cache: true,
        success: function(html){
            console.log("Morem ngaaa db");
        },
            error: function (errMsg){
                console.log(errMsg);
                //showModal("Porosia u shtua me sukses");
                displayCart();
            }

    });
    displayCart();

     
});



function showModal(msg){  
        var modal = document.getElementById("myModal2");
        var span1 = document.getElementsByClassName("close1")[0];

        // When the user clicks on the button, open the modal
        modal.style.display = "block";
        let modalDescription =document.querySelector("#modal-description");
        if(modalDescription){
            
            modalDescription.innerHTML= `${msg}`;
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
function displayCart(){
  
    $.ajax({
        type:"GET",
        url:"http://localhost:8090/xoen/financa", // endpoint-i  localhost:8090/xoen/financa
      
        dataType: 'json',
        cache: true,
        success: function(html){
                console.log("Morem nga db");
                financItems=html;
                    
                    
            let productContainer =document.querySelector(".products");
            if(productContainer){
                    let i=0;
                    productContainer.innerHTML='';
                
                    Object.values(financItems).map(item =>{
                        let status= (item.statusi==0)? 'Ne Pritje' :'Miraturar';
                        productContainer.innerHTML += `
                        <tr>
                                <td>${item.userId}</td>
                                <td>${item.id}</td>
                                <td>${item.comments}</td>
                                <td>${status}</td>
                                <td><button class="normal confirm" id="${i}">Konfirmo</button></td>
                                <td><button class="normal reject" id="${i}">Refuzo</button></td>
                            </tr>
                        `;
                        i++;

                    });
                    //Nese duam t i bejm confirm nje projecti
                    let confirmButtons = document.querySelectorAll('.confirm');
                    for (let i = 0; i < confirmButtons.length; i++){
                        confirmButtons[i].addEventListener('click',()=>{
                            console.log('dua te konfirmoj ',i);
                            confirmProject(financItems,i);
                            
                        });     
                    }

                    //Nese duam t i bejme reject nje projeti
                        let rejectButtons = document.querySelectorAll('.reject');
                    for (let i = 0; i < rejectButtons.length; i++){
                        rejectButtons[i].addEventListener('click',()=>{
                            console.log('dua te refuzoj',i);
                            rejectProject(financItems,i);
                           
                        });     
                    }
                }
            },
            error: function (errMsg){
                console.log(errMsg);
            }

});
}

function confirmProject(financItems,i){
   
    var check={
        id_project:financItems[i].id,
        status:1
    };
    // console.log(check);
     
     $.ajax({
        method:"PUT",
        url:"http://localhost:8090/xoen/financa/mirato?porosiaId="+financItems[i].id,  // endpoint-i  /xoen/financa/delete
        //data:check,
        //dataType: 'json',
        cache: true,
        success: function(html){
            console.log(html);//nese ka ndonje error ne php del ketu
            displayCart();
        },
        error: function(errMsg){
            console.log(errMsg);
        }

    });
    

}
function rejectProject(financItems,i){
    var check={
        id:financItems[i].id
    };
    $.ajax({
        method:"DELETE",
        url:"http://localhost:8090/xoen/financa/delete?id="+financItems[i].id,  // endpoint-i  /xoen/financa/delete
        //data:check,
        //dataType: 'json',
        cache: true,
        success: function(html){
            console.log("Morem nga db");
            //showModal(html);//nese ka ndonje error ne php del ketu
            displayCart();
        },
        error: function(errMsg){
            console.log(errMsg);
        }

    });
    
}





  

function getFinanceProject2 (){
 //nje array qe mban objekte
    var projects=[{
        UserId: 'xeon',
        id: 'f1',
        id_ing: 70,
        status:0
    },
    {
        UserId: 'xeon2',
        id: 'f2',
        id_ing: 70,
        status:1
    }];
   
    return projects;
  
}

