export enum ContainerState {
    DOWNLOAD, REMOVING_GARBAGE, RUNNING
}

export type Container = {
    name: string,
    containerState: ContainerState,
    uuid: string,
    owner: string
}
