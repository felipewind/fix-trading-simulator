export class NewOrderSingleModel {

    constructor(public clOrdId?: string,
                public symbol?: string,
                public side?: string,
                public orderQty?: number,
                public price?: number,
                public account?: string) {}
}