export interface Order {    
    orderID?: number,
    clOrdID?: number,
    origClOrdID?: number,
    side?: string,
    sideText?: string,
    ordStatus?: string,
    ordStatusText?: string,
    symbol?: string,
    price?: number,
    orderQty?: number,
    cumQty?: number    
}