var l=Object.defineProperty,c=Object.defineProperties;var f=Object.getOwnPropertyDescriptors;var n=Object.getOwnPropertySymbols;var C=Object.prototype.hasOwnProperty,b=Object.prototype.propertyIsEnumerable;var i=(a,t,o)=>t in a?l(a,t,{enumerable:!0,configurable:!0,writable:!0,value:o}):a[t]=o,r=(a,t)=>{for(var o in t||(t={}))C.call(t,o)&&i(a,o,t[o]);if(n)for(var o of n(t))b.call(t,o)&&i(a,o,t[o]);return a},p=(a,t)=>c(a,f(t));var e=(a,t,o)=>(i(a,typeof t!="symbol"?t+"":t,o),o);import{aM as T,a8 as m,cy as u}from"./index-ccbc2f92.js";import{d as E}from"./chartEditStore-45e1a76d.js";import{z as s,A as g,B as A}from"./index-717da49f.js";import"./plugin-a694cf20.js";import"./icon-821d6910.js";import"./SettingItem-abb1a26b.js";/* empty css                                                                      */import"./SettingItemBox-d2398725.js";import"./CollapseItem.vue_vue_type_script_setup_true_lang-08515eb0.js";import"./index.esm.min-ba907e3b.js";import"./http-2d25b213.js";import"./lodash-a46480a3.js";import"./fileTypeEnum-21359a08.js";const d={[u]:A.DATA,tabLabel:"选项1",tabType:"segment",dataset:[{label:"选项1",value:"1"},{label:"选项2",value:"2"},{label:"选项3",value:"3"}]};class B extends E{constructor(){super(...arguments);e(this,"key",s.key);e(this,"attr",p(r({},T),{w:460,h:32,zIndex:-1}));e(this,"chartConfig",m(s));e(this,"interactActions",g);e(this,"option",m(d))}}export{B as default,d as option};