import{d as b,Y as c,r as h,o,c as i,F as C,s as x,bb as t,am as d,bc as z,e as B,w,D as I,E as R,h as q}from"./index-c6cc3b14.js";import{i as F}from"./icon-8314374d.js";const g={class:"go-apple-control-btn"},L=["onClick"],M=b({__name:"index",props:{mini:{request:!1,type:Boolean,default:!1},disabled:{request:!1,type:Boolean,default:!1},hidden:{request:!1,type:Array,default(){return[]}},narrow:{request:!1,type:Boolean,default:!1}},emits:["close","remove","resize","fullResize"],setup(s,{emit:u}){const a=s,{CloseIcon:f,RemoveIcon:m,ResizeIcon:_}=F.ionicons5,p=c(()=>y.filter(r=>a.hidden.findIndex(l=>r.key==l)===-1)),v=c(()=>a.narrow&&t(!0)),y=[{title:"关闭",key:"close",icon:f},{title:"缩小",key:"remove",icon:m},{title:v.value?"缩小":"放大",key:a.narrow?"fullResize":"resize",icon:_}],k=e=>{e==="fullResize"&&t(),e==="remove"&&t(!0)&&t(),u(e)};return(e,r)=>{const l=h("n-icon");return o(),i("div",g,[(o(!0),i(C,null,x(p.value,n=>(o(),i("div",{key:n.key,class:d(["btn",[n.key,s.disabled&&"disabled",s.mini&&"mini"]]),onClick:z(D=>k(n.key),["stop"])},[B(l,{size:"10",class:d(["icon-base",{hover:!s.disabled}])},{default:w(()=>[(o(),I(R(n.icon)))]),_:2},1032,["class"])],10,L))),128))])}}});const N=q(M,[["__scopeId","data-v-281e8a22"]]);export{N as M};
