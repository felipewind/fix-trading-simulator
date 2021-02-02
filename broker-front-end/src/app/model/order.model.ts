export class Order {

    constructor(public clOrdId?: string,
                public symbol?: string,
                public side?: string,
                public orderQty?: number,
                public price?: number,
                private account?: string) {}
}