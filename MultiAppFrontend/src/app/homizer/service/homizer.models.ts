
export class homizerItem {
    public name: string;
    public description?: string;
    public image?: string;
    public number?: number;

    constructor(name: string, description?: string, image?: string, number?: number) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.number = number;
    }
}