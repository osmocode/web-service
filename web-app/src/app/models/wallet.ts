export interface WalletList {
  wallets: Wallet[];
}

export interface Wallet {
  currency: string;
  balance: number;
}

export interface WalletListGet {
  uuid: string;
}

export interface WalletGet {
  uuid: string;
  currency: string;
}

export interface WalletAdd {
  uuid: string;
  currency: string;
  value: number;
}

export interface WalletRemove {
  uuid: string;
  currency: string;
  value: number;
}
