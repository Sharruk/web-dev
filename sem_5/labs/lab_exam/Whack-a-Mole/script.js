let holes = document.querySelectorAll(".hole");
let score=0;
let timer;

function showMole()
{
    holes.forEach(function(hole)
    {
        hole.textContent="";
        hole.classList.remove("active");
        hole.onclick=null;
    });

    let random = Math.floor(Math.random()*holes.length);

    holes[random].textContent="";
    holes[random].classList.add("active");
    holes[random].onclick=function(){
        score++;

        document.getElementById("score").textContent=score;
        holes[random].textContent="";

        holes[random].classList.remove("active");
    };
}

function startGame(){
    score=0;

    document.getElementById("score").textContent="score";

    clearInterval(timer);
    showMole();
    timer=setInterval(showMole,1000);
}
