var l=Object.defineProperty,c=Object.defineProperties;var f=Object.getOwnPropertyDescriptors;var r=Object.getOwnPropertySymbols;var C=Object.prototype.hasOwnProperty,b=Object.prototype.propertyIsEnumerable;var i=(a,t,o)=>t in a?l(a,t,{enumerable:!0,configurable:!0,writable:!0,value:o}):a[t]=o,n=(a,t)=>{for(var o in t||(t={}))C.call(t,o)&&i(a,o,t[o]);if(r)for(var o of r(t))b.call(t,o)&&i(a,o,t[o]);return a},p=(a,t)=>c(a,f(t));var e=(a,t,o)=>(i(a,typeof t!="symbol"?t+"":t,o),o);import{aJ as T,a5 as s,cw as u}from"./index-c6cc3b14.js";import{d as E}from"./chartEditStore-3ee495ca.js";import{q as m,r as g,s as d}from"./index-68969097.js";import"./plugin-0dcb0bd6.js";import"./icon-8314374d.js";import"./SettingItem-ec2fd044.js";/* empty css                                                                       */import"./SettingItemBox-cf908047.js";import"./CollapseItem.vue_vue_type_script_setup_true_lang-81d1b4a3.js";import"./index.esm.min-4d91f069.js";import"./http-abbaebe7.js";import"./lodash-c32566df.js";import"./fileTypeEnum-21359a08.js";const A={[u]:d.DATA,tabLabel:"选项1",tabType:"segment",dataset:[{label:"选项1",value:"1"},{label:"选项2",value:"2"},{label:"选项3",value:"3"}]};class J extends E{constructor(){super(...arguments);e(this,"key",m.key);e(this,"attr",p(n({},T),{w:460,h:32,zIndex:-1}));e(this,"chartConfig",s(m));e(this,"interactActions",g);e(this,"option",s(A))}}export{J as default,A as option};