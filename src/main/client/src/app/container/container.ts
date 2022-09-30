export enum ContainerState {
    RUNNING,
    BUILDING,
    HUFF
}

export type Container = {
    name: string,
    containerState: ContainerState
    uuid: string;
}
