import{d as M,W as U,p as y,U as a,Y as g,r,o as s,D as c,w as t,e as n,u as j,c as f,F as C,s as S,E as h,q as b,t as v,aY as G,h as J}from"./index-c6cc3b14.js";import{g as Q}from"./plugin-0dcb0bd6.js";import{i as X}from"./icon-8314374d.js";import{u as Z}from"./useKeyboard.hook-09dba9eb.js";import{u as ee,a as te,H as l}from"./chartEditStore-3ee495ca.js";import{u as A,C as i}from"./chartLayoutStore-fa484834.js";const oe=M({__name:"index",setup(se){const{LayersIcon:H,BarChartIcon:B,PrismIcon:I,HomeIcon:L,ArrowBackIcon:w,ArrowForwardIcon:D}=X.ionicons5,{setItem:E}=A(),{getLayers:x,getCharts:F,getDetails:R}=U(A()),d=ee(),u=te(),K=y([{key:i.CHARTS,select:F,title:"图表组件",icon:a(B)},{key:i.LAYERS,select:x,title:"图层控制",icon:a(H)},{key:i.DETAILS,select:R,title:"详情设置",icon:a(I)}]),T=g(()=>u.getBackStack.length>1),z=g(()=>u.getForwardStack.length>0),W=y([{key:l.BACK_STACK,select:T,title:"后退",icon:a(w)},{key:l.FORWARD_STACK,select:z,title:"前进",icon:a(D)}]),Y=o=>o.key===i.DETAILS?o.select?"":"primary":o.select?"primary":"",$=o=>{E(o.key,!o.select)},q=o=>{switch(o.key){case l.BACK_STACK:d.setBack();break;case l.FORWARD_STACK:d.setForward();break}},N=()=>{Q({message:"返回将不会保存任何操作",isMaskClosable:!0,onPositiveCallback:()=>{G(),Z()}})};return(o,p)=>{const O=r("n-icon"),_=r("n-button"),k=r("n-tooltip"),P=r("n-divider"),m=r("n-space");return s(),c(m,{class:"header-left-btn",size:25},{default:t(()=>[n(_,{size:"small",quaternary:"",onClick:p[0]||(p[0]=e=>N())},{icon:t(()=>[n(O,{depth:3},{default:t(()=>[n(j(L))]),_:1})]),_:1}),n(m,null,{default:t(()=>[(s(!0),f(C,null,S(K,e=>(s(),c(k,{key:e.key,placement:"bottom",trigger:"hover"},{trigger:t(()=>[n(_,{size:"small",ghost:"",type:Y(e),focusable:!1,onClick:V=>$(e)},{default:t(()=>[(s(),c(h(e.icon)))]),_:2},1032,["type","onClick"])]),default:t(()=>[b("span",null,v(e.title),1)]),_:2},1024))),128)),n(P,{vertical:""}),(s(!0),f(C,null,S(W,e=>(s(),c(k,{key:e.key,placement:"bottom",trigger:"hover"},{trigger:t(()=>[n(_,{size:"small",ghost:"",type:"primary",disabled:!e.select,onClick:V=>q(e)},{default:t(()=>[(s(),c(h(e.icon)))]),_:2},1032,["disabled","onClick"])]),default:t(()=>[b("span",null,v(e.title),1)]),_:2},1024))),128))]),_:1})]),_:1})}}});const _e=J(oe,[["__scopeId","data-v-9bd0a48f"]]);export{_e as default};