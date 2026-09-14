let score=0;

let first=null;

let second=null;

let lock=false;

let cards=document.querySelectorAll(".card");

let list=[

"Apple","Apple",

"Mango","Mango",

"Orange","Orange",

"Banana","Banana",

"Grapes","Grapes",

"Cherry","Cherry",

"Guava","Guava",

"Pineapple","Pineapple"

];

// SHUFFLE

for(let i=0;i<list.length;i++){

    let j=Math.floor(Math.random()*list.length);

    let temp=list[i];

    list[i]=list[j];

    list[j]=temp;

}

// Assign shuffled values to cards

cards.forEach((card,index)=>{

    card.dataset.value=list[index];

});

cards.forEach(card=>{

card.onclick=function(){

if(lock) return;

if(card==first) return;

if(card.style.display=="none") return;

card.innerHTML=card.dataset.value;

if(first==null){

first=card;

}

else{

second=card;

lock=true;


if(first.dataset.value==second.dataset.value){

setTimeout(function(){

first.style.background="green";
second.style.background="green";

first.style.color="white";
second.style.color="white";

first.onclick=null;
second.onclick=null;

score=score+10;

first=null;
second=null;

lock=false;

if(score==80){

setTimeout(function(){

alert("Congratulations! You Won!");

location.reload();

},500);

}

},300);

}
else{

setTimeout(function(){

first.innerHTML="";

second.innerHTML="";

first=null;

second=null;

lock=false;

},1000);

}

}

};

});