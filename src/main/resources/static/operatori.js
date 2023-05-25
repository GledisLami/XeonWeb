console.log('running operator');

displayProjects();
function displayProjects(){
    
     $.ajax({
        type:"GET",
        url:"http://localhost:8090/xoen/inxhinieri", // endpoint-i  localhost:8090/xoen/financa
        //dataType: 'json',
        cache: true,
        success: function(html){
                    console.log("Morem nga db");
               
                    let projects=html;
                    let productContainer =document.querySelector(".products");
                    if(productContainer){
                        let i=0;
                        productContainer.innerHTML='';
                        Object.values(projects).map(item =>{
                            
                            productContainer.innerHTML += ` 
                            <tr>
                                    <td>${item.id}</td>
                                    <td>${item.progresi_proceseve}</td>
                                    <td><button  class="normal confirm" id="${i}">Shiko proceset</button></td>
                                    
                                </tr>
                            `;
                            i++;

                        });

                       //Nese duam te shofim proceset e nje projeti
                        let processButtons = document.querySelectorAll('.confirm');
                    for (let i = 0; i < processButtons.length; i++){
                        processButtons[i].addEventListener('click',()=>{
                            localStorage.setItem('projectId',projects[i].id);
                            window.location.href='./listProceset.html';
                        });     
                    }



                    }
                  

      
        },      
        error: function (errMsg){
                console.log('ka error');
            }

        });
    
   


 }


function getFinanceProject2 (){
 
    var projects=[{
        name: 'Projekt1',
        project: '1',
        koha: '-',        
    },
    {
        name: 'Projekt2',
        project: '2',
        koha: '-',     
    },
    {
        name: 'Projekt3',
        project: '3',
        koha: '-',       
    },
    {
        name: 'Projekt4',
        project: '4',
        koha: '-',
    }];
   
    return projects;
  
}

