
export class HomizerItem {
    public id: string;
    public name: string;
    public description?: string;
    public image?: string;
    public number: number;

    constructor(id: string, name: string, description?: string, image?: string, number?: number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.number = number;
    }
}