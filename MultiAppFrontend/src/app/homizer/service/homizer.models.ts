
export class HomizerItem {
    public id: string;
    public name: string;
    public description?: string;
    public image?: string;
    public number?: number;

    constructor(name: string, description?: string, image?: string, number?: number, id?: string) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.number = number;
    }
}
