export class Order {
	constructor(public id?: number,
        public symbolCode?: string,
        public symbolName?: string,
        public sideCode?: string,
        public sideName?: string,
        public orderQty?: number,
        public price?: number,
        public account?: string) { }
}