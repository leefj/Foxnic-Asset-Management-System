(self.webpackChunktansci_boot_ui_editor=self.webpackChunktansci_boot_ui_editor||[]).push([[2475],{34856:function(t,e,n){!function(t){"use strict";function e(t,e){if(!t.hasOwnProperty(e))throw new Error("Undefined state "+e+" in simple mode")}function n(t,e){if(!t)return/(?:)/;var n="";return t instanceof RegExp?(t.ignoreCase&&(n="i"),t.unicode&&(n+="u"),t=t.source):t=String(t),new RegExp((!1===e?"":"^")+"(?:"+t+")",n)}function a(t,a){(t.next||t.push)&&e(a,t.next||t.push),this.regex=n(t.regex),this.token=function(t){if(!t)return null;if(t.apply)return t;if("string"==typeof t)return t.replace(/\./g," ");for(var e=[],n=0;n<t.length;n++)e.push(t[n]&&t[n].replace(/\./g," "));return e}(t.token),this.data=t}function r(t,e){return function(n,a){if(a.pending){var r=a.pending.shift();return 0==a.pending.length&&(a.pending=null),n.pos+=r.text.length,r.token}if(a.local){if(a.local.end&&n.match(a.local.end)){var o=a.local.endToken||null;return a.local=a.localState=null,o}var l;return o=a.local.mode.token(n,a.localState),a.local.endScan&&(l=a.local.endScan.exec(n.current()))&&(n.pos=n.start+l.index),o}for(var s=t[a.state],c=0;c<s.length;c++){var d=s[c],u=(!d.data.sol||n.sol())&&n.match(d.regex);if(u){d.data.next?a.state=d.data.next:d.data.push?((a.stack||(a.stack=[])).push(a.state),a.state=d.data.push):d.data.pop&&a.stack&&a.stack.length&&(a.state=a.stack.pop()),d.data.mode&&i(e,a,d.data.mode,d.token),d.data.indent&&a.indent.push(n.indentation()+e.indentUnit),d.data.dedent&&a.indent.pop();var p=d.token;if(p&&p.apply&&(p=p(u)),u.length>2&&d.token&&"string"!=typeof d.token){for(var f=2;f<u.length;f++)u[f]&&(a.pending||(a.pending=[])).push({text:u[f],token:d.token[f-1]});return n.backUp(u[0].length-(u[1]?u[1].length:0)),p[0]}return p&&p.join?p[0]:p}}return n.next(),null}}function o(t,e){if(t===e)return!0;if(!t||"object"!=typeof t||!e||"object"!=typeof e)return!1;var n=0;for(var a in t)if(t.hasOwnProperty(a)){if(!e.hasOwnProperty(a)||!o(t[a],e[a]))return!1;n++}for(var a in e)e.hasOwnProperty(a)&&n--;return 0==n}function i(e,a,r,i){var l;if(r.persistent)for(var s=a.persistentStates;s&&!l;s=s.next)(r.spec?o(r.spec,s.spec):r.mode==s.mode)&&(l=s);var c=l?l.mode:r.mode||t.getMode(e,r.spec),d=l?l.state:t.startState(c);r.persistent&&!l&&(a.persistentStates={mode:c,spec:r.spec,state:d,next:a.persistentStates}),a.localState=d,a.local={mode:c,end:r.end&&n(r.end),endScan:r.end&&!1!==r.forceEnd&&n(r.end,!1),endToken:i&&i.join?i[i.length-1]:i}}function l(e,n){return function(a,r,o){if(a.local&&a.local.mode.indent)return a.local.mode.indent(a.localState,r,o);if(null==a.indent||a.local||n.dontIndentStates&&function(t,e){for(var n=0;n<e.length;n++)if(e[n]===t)return!0}(a.state,n.dontIndentStates)>-1)return t.Pass;var i=a.indent.length-1,l=e[a.state];t:for(;;){for(var s=0;s<l.length;s++){var c=l[s];if(c.data.dedent&&!1!==c.data.dedentIfLineStart){var d=c.regex.exec(r);if(d&&d[0]){i--,(c.next||c.push)&&(l=e[c.next||c.push]),r=r.slice(d[0].length);continue t}}}break}return i<0?0:a.indent[i]}}t.defineSimpleMode=function(e,n){t.defineMode(e,(function(e){return t.simpleMode(e,n)}))},t.simpleMode=function(n,o){e(o,"start");var i={},s=o.meta||{},c=!1;for(var d in o)if(d!=s&&o.hasOwnProperty(d))for(var u=i[d]=[],p=o[d],f=0;f<p.length;f++){var h=p[f];u.push(new a(h,o)),(h.indent||h.dedent)&&(c=!0)}var g={startState:function(){return{state:"start",pending:null,local:null,localState:null,indent:c?[]:null}},copyState:function(e){var n={state:e.state,pending:e.pending,local:e.local,localState:null,indent:e.indent&&e.indent.slice(0)};e.localState&&(n.localState=t.copyState(e.local.mode,e.localState)),e.stack&&(n.stack=e.stack.slice(0));for(var a=e.persistentStates;a;a=a.next)n.persistentStates={mode:a.mode,spec:a.spec,state:a.state==e.localState?n.localState:t.copyState(a.mode,a.state),next:n.persistentStates};return n},token:r(i,n),innerMode:function(t){return t.local&&{mode:t.local.mode,state:t.localState}},indent:l(i,s)};if(s)for(var S in s)s.hasOwnProperty(S)&&(g[S]=s[S]);return g}}(n(15237))}}]);