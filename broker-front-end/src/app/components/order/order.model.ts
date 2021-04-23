export interface Order {    
    clOrdId?: number,
    side?: string,
    sideText?: string,
    ordStatus?: string,
    ordStatusText?: string,
    symbol?: string,
    price?: number,
    orderQty?: number,
    cumQty?: number    
}