export interface ToplistItem {
  rank: number;
  title: string;
  description?: string;
}

export interface Toplist {
  id: number;
  name: string;
  type: 'film' | 'zene' | 'sorozat' | string;
  items: ToplistItem[];
}