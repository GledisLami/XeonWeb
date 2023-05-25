console.log('running studio');

displayProjects();

function displayProjects(){
     $.ajax({
        type:"GET",
        url:"http://localhost:8090/xoen/inxhinieri", // endpoint-i  localhost:8090/xoen/inxhinieri
        dataType: 'json',
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
                                    <td>${item.afati}</td>
                                    <td>${item.progresi_proceseve}</td>
                                    <!--<td><input type="file" id="${i}" name="file" class= "normal"></td>-->
                                    <td><button  class="normal confirm" id="${i}">Zgjidh proceset</button></td>       
                                </tr>
                            `;
                            i++;
                        });
                       //Nese duam te shofim proceset e nje projeti
                        let processButtons = document.querySelectorAll('.confirm');
                    for (let i = 0; i < processButtons.length; i++){
                        processButtons[i].addEventListener('click',()=>{
                            // console.log('dua te refuzoj',i);
                            localStorage.setItem('projectId',projects[i].id);
                            window.location.href='./proceset.html';
                        });     
                    }
                    }
        },      
        error: function (errMsg){
                console.log('ka error');
            }
        });
 }

