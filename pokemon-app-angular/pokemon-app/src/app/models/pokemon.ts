import { BaseStats } from './baseStats';

export class Pokemon {
    id: number;
    pokedex: number;
    name: string;
    form: string;
    baseStats: BaseStats;
    types: string[];
    gen: number;
    shadow: boolean;
}